package com.example.myapplication;

import android.os.AsyncTask;


public class FetchAPIToken extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        String uri = strings[0];
        String method = strings[1];
        String token = strings[2];

        return NetworkUtils.doRequest(uri,method,null,token);
    }
}
