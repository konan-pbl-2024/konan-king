package com.example.konan_king;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MonadaiActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mondai);
        Button quiz_button = (Button)findViewById(R.id.quiz_button);
        Button ans1 = (Button)findViewById(R.id.ans1);
        Button ans2 = (Button)findViewById(R.id.ans2);
        Button ans3 = (Button)findViewById(R.id.ans3);
        Button ans4 = (Button)findViewById(R.id.ans4);

        //            quiz_buttonを押したときに4択を表示する
        quiz_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ans1.setVisibility(View.VISIBLE);
                ans2.setVisibility(View.VISIBLE);
                ans3.setVisibility(View.VISIBLE);
                ans4.setVisibility(View.VISIBLE);

            }
        });
        //           ans1を押したときに画面遷移する
        ans1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new
                        Intent(MonadaiActivity.this, SeikaiActivity.class);
                startActivity(intent);
            }
        });

        //            ans2を押したときに画面遷移する
        ans2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new
                        Intent(MonadaiActivity.this, MatigaiActivity.class);
                startActivity(intent);
            }
        });

        //            ans3を押したときに画面遷移する
        ans3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new
                        Intent(MonadaiActivity.this, MatigaiActivity.class);
                startActivity(intent);
            }
        });

        //            ans4を押したときに画面遷移する
        ans4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new
                        Intent(MonadaiActivity.this, MatigaiActivity.class);
                startActivity(intent);
            }
        });
    }
}
