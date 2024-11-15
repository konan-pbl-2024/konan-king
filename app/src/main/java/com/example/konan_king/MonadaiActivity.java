package com.example.konan_king;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MonadaiActivity extends AppCompatActivity {

    private TextView timeTextView;  // 経過時間を表示するTextView
    private TextView quizSentTextView;  // 文字列を表示するTextView
    private TextView textView;  // 問題番号を表示するTextView
    private int seconds = 0;  // 経過時間（秒）
    private int currentCharIndex = 0;  // 表示する文字列のインデックス
    private String targetText = "これはサンプルの日本語文章です。ここに文字列が表示されます。50字程度の文章として表示されることを目的としています。";
    private Handler handler = new Handler();  // メインスレッドに遅延実行するためのHandler
    private int currentQuestion = 1;  // 問題番号（デフォルトは1）

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mondai);

        // timeTextView を初期化（経過時間表示用）
        timeTextView = findViewById(R.id.text_test);  // IDを確認
        quizSentTextView = findViewById(R.id.quiz_sent);  // 文字列を表示するTextView
        textView = findViewById(R.id.quiz_num);  // 問題番号を表示するTextView

        // 問題番号をIntentから取得
        Intent intent = getIntent();
        currentQuestion = intent.getIntExtra("currentQuestion", 1);  // 既定値は1

        // 問題番号が10問を超えないように制限
        if (currentQuestion > 10) {
            currentQuestion = 10;
        }

        // 問題番号を表示
        textView.setText("第" + currentQuestion + "問");

        // ボタンや他のUI要素の初期化
        Button okButton = findViewById(R.id.ans1);
        Button quiz_button = findViewById(R.id.quiz_button);
        Button ans1 = findViewById(R.id.ans1);
        Button ans2 = findViewById(R.id.ans2);
        Button ans3 = findViewById(R.id.ans3);
        Button ans4 = findViewById(R.id.ans4);

        // 1秒ごとに経過時間を更新するRunnableを作成
        Runnable timeRunnable = new Runnable() {
            @Override
            public void run() {
                // 経過時間を更新
                seconds++;
                timeTextView.setText("経過時間: " + seconds + "秒");
                handler.postDelayed(this, 1000);  // 1秒後に再度実行
            }
        };

        // 100ミリ秒ごとに文字列を1文字ずつ表示するRunnableを作成
        Runnable textRunnable = new Runnable() {
            @Override
            public void run() {
                if (currentCharIndex < targetText.length()) {
                    String displayedText = targetText.substring(0, currentCharIndex + 1);
                    quizSentTextView.setText(displayedText);
                    currentCharIndex++;
                }
                handler.postDelayed(this, 100);  // 100ミリ秒後に再度実行
            }
        };

        // 最初に2つのRunnableを実行
        handler.post(timeRunnable);  // 時間経過を更新するRunnable
        handler.post(textRunnable);  // 文字列を1文字ずつ表示するRunnable

        // quiz_buttonを押したときに4択を表示する
        quiz_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans1.setVisibility(View.VISIBLE);
                ans2.setVisibility(View.VISIBLE);
                ans3.setVisibility(View.VISIBLE);
                ans4.setVisibility(View.VISIBLE);
            }
        });

        // 各選択肢を押したときに次の問題に進む処理
        ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MonadaiActivity.this, MonadaiActivity.class);
                intent.putExtra("currentQuestion", currentQuestion + 1);  // 次の問題番号を渡す
                startActivity(intent);
            }
        });

        ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MonadaiActivity.this, MonadaiActivity.class);
                intent.putExtra("currentQuestion", currentQuestion + 1);  // 次の問題番号を渡す
                startActivity(intent);
            }
        });

        ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MonadaiActivity.this, MonadaiActivity.class);
                intent.putExtra("currentQuestion", currentQuestion + 1);  // 次の問題番号を渡す
                startActivity(intent);
            }
        });

        ans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MonadaiActivity.this, MonadaiActivity.class);
                intent.putExtra("currentQuestion", currentQuestion + 1);  // 次の問題番号を渡す
                startActivity(intent);
            }
        });
    }
}