package com.example.torridas.atest;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements AsyncResponseGitHubTask{
    private String query;
    private Button buttonSearch;
    private EditText editText;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        buttonSearch = (Button)findViewById(R.id.btn_cautare);
        editText = (EditText)findViewById(R.id.input);
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setVisibility(View.INVISIBLE);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query = editText.getText().toString();
                if(TextUtils.isEmpty(query)) {
                   Toast toast = Toast.makeText(SearchActivity.this,"Insert something!",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    //Toast toast = Toast.makeText(SearchActivity.this,query,Toast.LENGTH_SHORT);
                    SearchGITHubTask search = new SearchGITHubTask();
                    char[] queryArray = query.toCharArray();
                    //toast.show();

                    for( int i = 0; i < query.length()-1; i++ ) {
                        if( queryArray[i] == ' ') {
                            queryArray[i] = '+';
                        }
                    }
                    query = String.valueOf(queryArray);
                    //Toast toast2 = Toast.makeText(SearchActivity.this,query,Toast.LENGTH_SHORT);
                    //toast2.show();

                    query = Strings.GITHUBAPIURL + query;
                    search.setResponse(SearchActivity.this);
                    search.execute(query);
                }
            }
        });

    }

    @Override
    public void finishTask(List<Result> resultList) {
        ResultAdapter resultAdapter = new ResultAdapter(resultList);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(resultAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

    }
}
