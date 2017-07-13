package com.example.torridas.atest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements AsyncResponseGitHubTask{
    private String query;
    private Button buttonSearch;
    private AutoCompleteTextView autoCompleteTextView;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        progressDialog = new ProgressDialog(SearchActivity.this );
        buttonSearch = (Button)findViewById(R.id.btn_cautare);
        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.input);
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setVisibility(View.INVISIBLE);


        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String query = autoCompleteTextView.getText().toString();
                if( query.length() > 2 ) {
                    query.replace(' ','+');
                    query = Strings.GITHUBAPIURL + query;
                    SearchGITHubTask search = new SearchGITHubTask(SearchActivity.this);
                    search.execute(query);
                }
            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                query = autoCompleteTextView.getText().toString();
                if(TextUtils.isEmpty(query)) {
                   Toast toast = Toast.makeText(SearchActivity.this,"Insert something!",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    //Toast toast = Toast.makeText(SearchActivity.this,query,Toast.LENGTH_SHORT);
                    //toast.show();
                    SearchGITHubTask search = new SearchGITHubTask(SearchActivity.this, progressDialog );
                    query = query.replace(' ', '+');
                    //Toast toast2 = Toast.makeText(SearchActivity.this,query,Toast.LENGTH_SHORT);
                    //toast2.show();
                    query = Strings.GITHUBAPIURL + query;
                    search.execute(query);
                }
            }
        });

    }

    @Override
    public void finishTask(ResultCollection resultCollection) {
        ResultAdapter resultAdapter = new ResultAdapter(resultCollection.getResultList());
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(resultAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

    }

    @Override
    public void finishTask2(ResultCollection resultCollection) {
        CostumAutoCompleteAdapter costumAutoCompleteAdapter = new CostumAutoCompleteAdapter(SearchActivity.this,
                R.layout.result_item, resultCollection.getResultList());
        autoCompleteTextView.setAdapter(costumAutoCompleteAdapter);
        autoCompleteTextView.showDropDown();
    }

}
