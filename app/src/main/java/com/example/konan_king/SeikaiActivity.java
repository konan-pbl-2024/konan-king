package com.example.konan_king;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SeikaiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seikai);
        int currentQuestion = getIntent().getIntExtra("currentQuestion", 0);
        int seconds = getIntent().getIntExtra("seconds", 0);

        Button next1 = findViewById(R.id.next1);

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeikaiActivity.this, MonadaiActivity.class);
                intent.putExtra("currentQuestion", currentQuestion);  // 問題番号を更新するためのフラグを渡す
                intent.putExtra("seconds", seconds);

                startActivity(intent);
            }
        });
    }
}
