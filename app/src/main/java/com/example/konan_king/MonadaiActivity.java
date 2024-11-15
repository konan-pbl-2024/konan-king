package com.example.konan_king;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;  // Intentをインポート
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MonadaiActivity extends AppCompatActivity {

    private TextView timeTextView;  // 経過時間を表示するTextView
    private TextView quizSentTextView;  // 文字列を表示するTextView
    private int seconds = 0;  // 経過時間（秒）
    private int currentCharIndex = 0;  // 表示する文字列のインデックス
    private ArrayList<String[]> quizDataList = new ArrayList<>();  // 問題データを格納するリスト
    private int currentQuizIndex = 0;  // 現在の問題のインデックス
    private Handler handler = new Handler();  // メインスレッドに遅延実行するためのHandler

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mondai);

        // 問題文の読み取り
        try {
            InputStream in = getResources().getAssets().open("sample.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                // 各行をカンマで分割して配列に格納
                String[] columns = line.split(",");
                if (columns.length == 6) {
                    quizDataList.add(columns);  // 正しい行の場合はリストに追加
                }
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // ボタンや他のUI要素の初期化
        Button quiz_button = findViewById(R.id.quiz_button);
        Button ans1 = findViewById(R.id.ans1);
        Button ans2 = findViewById(R.id.ans2);
        Button ans3 = findViewById(R.id.ans3);
        Button ans4 = findViewById(R.id.ans4);

        // 文字列を表示するTextViewの参照を取得
        TextView textView1 = findViewById(R.id.quiz_sent);
        textView1.setText("OK! NICE!!");

        // 経過時間を表示するTextViewの参照を取得
        timeTextView = findViewById(R.id.text_test);
        // 文字列を表示するTextViewの参照を取得
        quizSentTextView = findViewById(R.id.quiz_sent);

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

        // 100ミリ秒ごとに文字列を1文字ずつ表示するRunnableを作成
        Runnable textRunnable = new Runnable() {
            @Override
            public void run() {
                // 現在の問題データを取得
                String[] currentQuizData = quizDataList.get(currentQuizIndex);
                String targetText = currentQuizData[0];  // 問題文
                String displayedText = targetText.substring(0, currentCharIndex + 1);  // 1文字ずつ表示
                quizSentTextView.setText(displayedText);  // 表示更新

                // インデックスを次に進める
                if (currentCharIndex < targetText.length()) {
                    currentCharIndex++;
                } else {
                    // 問題文の表示が終わったら、選択肢を表示する
                    Button ans1 = findViewById(R.id.ans1);
                    Button ans2 = findViewById(R.id.ans2);
                    Button ans3 = findViewById(R.id.ans3);
                    Button ans4 = findViewById(R.id.ans4);
                    ans1.setVisibility(View.VISIBLE);
                    ans2.setVisibility(View.VISIBLE);
                    ans3.setVisibility(View.VISIBLE);
                    ans4.setVisibility(View.VISIBLE);

                    // 選択肢を設定
                    ans1.setText(currentQuizData[1]);
                    ans2.setText(currentQuizData[2]);
                    ans3.setText(currentQuizData[3]);
                    ans4.setText(currentQuizData[4]);
                }

                // 100ミリ秒後に再度このRunnableを実行
                handler.postDelayed(this, 100);  // 100ミリ秒 = 0.1秒
            }
        };

        // 最初に2つのRunnableを実行
        handler.post(timeRunnable);  // 時間経過を更新するRunnable
        handler.post(textRunnable);  // 文字列を1文字ずつ表示するRunnable

        // quiz_buttonを押したときに4択を表示する
        quiz_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 問題を順番に表示
                if (currentQuizIndex < quizDataList.size()) {
                    currentQuizIndex++;  // 次の問題へ進む
                    currentCharIndex = 0;  // 文字表示をリセット
                    handler.post(textRunnable);  // 次の問題を表示
                }
            }
        });

        // 各選択肢を押したときに画面遷移する
        ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] currentQuizData = quizDataList.get(currentQuizIndex);
                if ("1".equals(currentQuizData[5])) {
                    // 正解ならSeikaiActivityへ遷移
                    Intent intent = new Intent(MonadaiActivity.this, SeikaiActivity.class);
                    startActivity(intent);
                } else {
                    // 間違いならMatigaiActivityへ遷移
                    Intent intent = new Intent(MonadaiActivity.this, MatigaiActivity.class);
                    startActivity(intent);
                }
            }
        });

        ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] currentQuizData = quizDataList.get(currentQuizIndex);
                if ("2".equals(currentQuizData[5])) {
                    // 正解ならSeikaiActivityへ遷移
                    Intent intent = new Intent(MonadaiActivity.this, SeikaiActivity.class);
                    startActivity(intent);
                } else {
                    // 間違いならMatigaiActivityへ遷移
                    Intent intent = new Intent(MonadaiActivity.this, MatigaiActivity.class);
                    startActivity(intent);
                }
            }
        });

        ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] currentQuizData = quizDataList.get(currentQuizIndex);
                if ("3".equals(currentQuizData[5])) {
                    // 正解ならSeikaiActivityへ遷移
                    Intent intent = new Intent(MonadaiActivity.this,
