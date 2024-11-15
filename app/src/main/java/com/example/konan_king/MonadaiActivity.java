package com.example.konan_king;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
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
    private TextView textView;  // 問題番号を表示するTextView
    private int seconds = 0;  // 経過時間（秒）
    private int currentCharIndex = 0;  // 表示する文字列のインデックス
    private ArrayList<String[]> quizDataList = new ArrayList<>();  // 問題データを格納するリスト
    private int currentQuizIndex = 0;  // 現在の問題のインデックス
    private Handler handler = new Handler();  // メインスレッドに遅延実行するためのHandler
    private int currentQuestion = 1;  // 問題番号（デフォルトは1）
    private Runnable timeRunnable; // 経過時間を更新するRunnable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mondai);

        // timeTextView を初期化（経過時間表示用）
        timeTextView = findViewById(R.id.text_test);  // IDを確認
        quizSentTextView = findViewById(R.id.quiz_sent);  // 文字列を表示するTextView
        textView = findViewById(R.id.quiz_num);  // 問題番号を表示するTextView

        // 問題番号と経過時間をIntentから取得
        Intent intent = getIntent();
        currentQuestion = intent.getIntExtra("currentQuestion", 1);  // 既定値は1
        seconds = intent.getIntExtra("seconds", 0);  // 既定値は0秒

        // 問題番号が10問を超えないように制限
        if (currentQuestion > 10) {
            currentQuestion = 10;
        }

        // 問題番号を表示
        textView.setText("第" + currentQuestion + "問");

        // 問題文の読み取り
        try {
            InputStream in = getResources().getAssets().open("mondaoi1.txt");
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

        // 経過時間を表示するTextViewの参照を取得
        timeTextView = findViewById(R.id.text_test);

        // 1秒ごとに経過時間を更新するRunnableを作成
        timeRunnable = new Runnable() {
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

                // 現在の問題データを取得
                String[] currentQuizData = quizDataList.get(currentQuizIndex);
                String targetText = currentQuizData[0];  // 問題文

                // インデックスを次に進める
                if (currentCharIndex < targetText.length()) {
                    String displayedText = targetText.substring(0, currentCharIndex + 1);
                    quizSentTextView.setText(displayedText);
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
                handler.postDelayed(this, 100);  // 100ミリ秒後に再度実行
            }
        };

        // 最初に2つのRunnableを実行
        handler.post(timeRunnable);  // 時間経過を更新するRunnable
        handler.post(textRunnable);  // 文字列を1文字ずつ表示するRunnable

        // quiz_buttonを押したときに4択を表示する
        // quiz_buttonを押したときの処理を修正
        // quiz_buttonを押したときの処理を修正
        quiz_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // textRunnableを停止して、問題文の表示を止める
                handler.removeCallbacks(textRunnable);

                // 現在の問題データを取得
                if (currentQuizIndex < quizDataList.size()) {
                    String[] currentQuizData = quizDataList.get(currentQuizIndex);

                    // 選択肢を表示
                    ans1.setVisibility(View.VISIBLE);
                    ans2.setVisibility(View.VISIBLE);
                    ans3.setVisibility(View.VISIBLE);
                    ans4.setVisibility(View.VISIBLE);

                    // 選択肢のテキストを設定
                    ans1.setText(currentQuizData[1]);
                    ans2.setText(currentQuizData[2]);
                    ans3.setText(currentQuizData[3]);
                    ans4.setText(currentQuizData[4]);

                    // quiz_buttonを無効化（連続で押されないようにする）
                    quiz_button.setEnabled(false);
                }
            }
        });

        // 各選択肢を押したときに次の問題に進む処理
        ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });

        ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });

        ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });

        ans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });
    }

    // 次の問題に進むメソッド
    private void nextQuestion() {
        currentQuestion++;  // 問題番号をインクリメント

        // 問題番号が10を超えないように制限
        if (currentQuestion > 10) {
            currentQuestion = 10;
        }

        // 次の問題に進むためのIntentを作成
        Intent intent = new Intent(MonadaiActivity.this, MonadaiActivity.class);
        intent.putExtra("currentQuestion", currentQuestion);  // 更新された問題番号を渡す
        intent.putExtra("seconds", seconds);  // 継続する経過時間を渡す
        startActivity(intent);  // 次の問題に進む
    }
}
