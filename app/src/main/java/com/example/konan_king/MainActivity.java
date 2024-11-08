package com.example.konan_king;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start_button = (Button)findViewById(R.id.start_button);
            start_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new
                            Intent(MainActivity.this, MonadaiActivity.class);
                    startActivity(intent);
                }
            });


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("");
        progressDialog.setMessage("ロード中...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        AsyncTaskClass task = new AsyncTaskClass(progressDialog);
        task.execute("");



    }
}