package com.example.torridas.atest;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Collection;
import java.util.List;

/**
 * Created by Torridas on 11-Jul-17.
 */

public class SearchGITHubTask extends AsyncTask<String, Integer, Boolean> {

    private AsyncResponseGitHubTask response;

    //mai trebuia sa verific daca metoda e implementata ca sa fie 100% safe
    public void setResponse(AsyncResponseGitHubTask response) {
        this.response = response;
    }

    private URL url;
    private List<Result> results;
    private StringBuilder builder;


    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        response.finishTask(results);
    }

    @Override
    protected Boolean doInBackground(String... params) {

        try {
            url = new URL(params[0]);
            HttpURLConnection gitHubConnection = (HttpURLConnection) url.openConnection();

            try {
                gitHubConnection.connect();
                InputStreamReader in = new InputStreamReader(gitHubConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(in);
                builder = new StringBuilder();
                String line;
                while(( line = bufferedReader.readLine()) != null ){
                    builder.append(line);
                }
                String stringResult = builder.toString();
                stringResult = stringResult.substring(stringResult.indexOf('[') );
                Gson gson = new GsonBuilder().setLenient().create();
                Type type = new TypeToken<List<Result>>(){}.getType();
                stringResult = stringResult.substring(0, stringResult.length() - 1 );
                results = gson.fromJson(stringResult, type);

            } catch (Exception e ) {
                Log.d(Strings.READINGTAG, "Failed reading from URL! ");
            }
        }
        catch ( Exception e) {
            Log.d(Strings.RESTAG, "Failed to connect!");
        }

        return null;
    }
}
