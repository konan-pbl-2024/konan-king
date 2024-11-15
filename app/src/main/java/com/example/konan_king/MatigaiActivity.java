package com.example.konan_king;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MatigaiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matigai);
        int currentQuestion = getIntent().getIntExtra("currentQuestion", 0);
        int seconds = getIntent().getIntExtra("seconds", 0);

        Button next2 = findViewById(R.id.next2);

        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MatigaiActivity.this, MonadaiActivity.class);
                intent.putExtra("currentQuestion", currentQuestion);  // 問題番号を更新するためのフラグを渡す
                intent.putExtra("seconds", seconds);
                startActivity(intent);
            }
        });
    }
}
