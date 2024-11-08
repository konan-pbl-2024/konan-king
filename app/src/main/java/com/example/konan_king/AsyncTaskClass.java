package com.example.konan_king;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class AsyncTaskClass extends AsyncTask<String, String, String> {

    ProgressDialog progressDialog;

    public AsyncTaskClass(ProgressDialog progressDialog) {
        super();
        this.progressDialog   = progressDialog;
    }

    @Override
    protected String doInBackground (String... params) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) { }
        return "";
    }

    @Override
    protected void onPostExecute(String str) {
        progressDialog.dismiss();
    }
}

