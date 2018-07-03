package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {
    TwitterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApp.getRestClient(this);
        setContentView(R.layout.activity_compose);
        Button btnExample = (Button) findViewById(R.id.button);
        btnExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText simpleEditText = (EditText) findViewById(R.id.et_simple);
                String strValue = simpleEditText.getText().toString();
                client.sendTweet(strValue, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
            }
        });
    }


    // ActivityTwo.java
    public void onSubmit(View v) {
        // closes the activity and returns to first screen
        this.finish();
    }


}
