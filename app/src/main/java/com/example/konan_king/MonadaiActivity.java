package com.example.konan_king;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

public class MonadaiActivity extends AppCompatActivity {

    private TextView timeTextView;  // 経過時間を表示するTextView
    private TextView quizSentTextView;  // 文字列を表示するTextView
    private int seconds = 0;  // 経過時間（秒）
    private int currentCharIndex = 0;  // 表示する文字列のインデックス
    private String targetText = "これはサンプルの日本語文章です。ここに文字列が表示されます。50字程度の文章として表示されることを目的としています。";
    private Handler handler = new Handler();  // メインスレッドに遅延実行するためのHandler

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mondai);

        // ボタンや他のUI要素の初期化（必要に応じて）
        Button okButton = findViewById(R.id.ans1);
        TextView textView1 = findViewById(R.id.quiz_sent);
        textView1.setText("OK! NICE!!");

        // 経過時間を表示するTextViewの参照を取得
        timeTextView = findViewById(R.id.text_test);  // timeTextViewのIDを使用
        // 文字列を表示するTextViewの参照を取得
        quizSentTextView = findViewById(R.id.quiz_sent);  // quiz_sentのIDを使用

        // 1秒ごとに経過時間を更新するRunnableを作成
        Runnable timeRunnable = new Runnable() {
            @Override
            public void run() {
                // 経過時間を更新
                seconds++;

                // 経過時間をTextViewに表示
                timeTextView.setText("経過時間: " + seconds + "秒");

                // 1秒後に再度このRunnableを実行
                handler.postDelayed(this, 1000);  // 1000ミリ秒 = 1秒
            }
        };

        // 500ミリ秒ごとに文字列を1文字ずつ表示するRunnableを作成
        Runnable textRunnable = new Runnable() {
            @Override
            public void run() {
                // 文字列の表示位置を更新
                if (currentCharIndex < targetText.length()) {
                    // 1文字ずつ表示
                    String displayedText = targetText.substring(0, currentCharIndex + 1);
                    quizSentTextView.setText(displayedText);

                    // インデックスを次に進める
                    currentCharIndex++;
                }

                // 500ミリ秒後に再度このRunnableを実行
                handler.postDelayed(this, 100);  // 500ミリ秒 = 0.5秒
            }
        };

        // 最初に2つのRunnableを実行
        handler.post(timeRunnable);  // 時間経過を更新するRunnable
        handler.post(textRunnable);  // 文字列を1文字ずつ表示するRunnable
    }
}
