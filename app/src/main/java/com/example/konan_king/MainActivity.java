package com.example.konan_king;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("");
        progressDialog.setMessage("ロード中...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        AsyncTaskClass task = new AsyncTaskClass(progressDialog);
        task.execute("");
    }
}