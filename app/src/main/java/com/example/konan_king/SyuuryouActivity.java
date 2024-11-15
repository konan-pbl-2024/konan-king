package com.example.konan_king;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SyuuryouActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syuuryou);

        Button button = findViewById(R.id.Title);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ActivityBへ遷移
                startActivity(new Intent(SyuuryouActivity.this, MainActivity.class));

            }
        });
    }
}