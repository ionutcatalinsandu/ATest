package com.example.torridas.atest;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Torridas on 11-Jul-17.
 */

public class SearchGITHubTask extends AsyncTask<String, Integer, Boolean> {

    private AsyncResponseGitHubTask response;
    private ProgressDialog dialog;
    private ResultCollection result;

    public SearchGITHubTask(AsyncResponseGitHubTask response, ProgressDialog dialog ) {
        this.response = response;
        this.dialog = dialog;
    }

    public SearchGITHubTask(AsyncResponseGitHubTask response ){
        this.response = response;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //dialog.setMessage("Looking over the internet..");
        //dialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        //dialog.dismiss();
        response.finishTask2(result);
    }

    @Override
    protected Boolean doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            HttpURLConnection gitHubConnection = (HttpURLConnection) url.openConnection();

            try {
                gitHubConnection.connect();
                InputStreamReader in = new InputStreamReader(gitHubConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(in);
                StringBuilder builder = new StringBuilder();
                String line;
                while(( line = bufferedReader.readLine()) != null ){
                    builder.append(line);
                }
                String stringResult = builder.toString();
                Gson gson = new GsonBuilder().setLenient().create();
                Type type = new TypeToken<ResultCollection>(){}.getType();
                result = gson.fromJson(stringResult, type);


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
