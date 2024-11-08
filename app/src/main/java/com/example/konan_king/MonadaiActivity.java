package com.example.konan_king;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;

public class MonadaiActivity extends AppCompatActivity{

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mondai);
        Button okButton = (Button)findViewById(R.id.ans1);

        CharByCharTextView textView = new CharByCharTextView(this);
        textView.setTargetText("テスト用の文字列です。");


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("");
        progressDialog.setMessage("ロード中...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        AsyncTaskClass task = new AsyncTaskClass(progressDialog);
        task.execute("");
    }

}
