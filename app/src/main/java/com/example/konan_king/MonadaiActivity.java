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

    private TextView timeTextView;
    private TextView quizSentTextView;
    private TextView textView;
    private int seconds = 0;
    private int currentCharIndex = 0;
    private ArrayList<String[]> quizDataList = new ArrayList<>();
    private int currentQuizIndex = 0;
    private Handler handler = new Handler();
    private int currentQuestion = 1;
    private Runnable timeRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mondai);

        // timeTextView を初期化
        timeTextView = findViewById(R.id.text_test);
        quizSentTextView = findViewById(R.id.quiz_sent);
        textView = findViewById(R.id.quiz_num);

        // Intentから問題番号と経過時間を取得
        Intent intent = getIntent();
        currentQuestion = intent.getIntExtra("currentQuestion", 1);
        seconds = intent.getIntExtra("seconds", 0);

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
                String[] columns = line.split(",");
                if (columns.length == 6) {
                    quizDataList.add(columns);
                }
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // ボタンやUI要素の初期化
        Button quiz_button = findViewById(R.id.quiz_button);
        Button ans1 = findViewById(R.id.ans1);
        Button ans2 = findViewById(R.id.ans2);
        Button ans3 = findViewById(R.id.ans3);
        Button ans4 = findViewById(R.id.ans4);

        // 経過時間を表示するRunnable
        timeRunnable = new Runnable() {
            @Override
            public void run() {
                seconds++;
                timeTextView.setText("経過時間: " + seconds + "秒");
                handler.postDelayed(this, 1000);
            }
        };

        // 問題文の表示を1文字ずつ更新
        Runnable textRunnable = new Runnable() {
            @Override
            public void run() {
                String[] currentQuizData = quizDataList.get(currentQuizIndex);
                String targetText = currentQuizData[0];

                if (currentCharIndex < targetText.length()) {
                    String displayedText = targetText.substring(0, currentCharIndex + 1);
                    quizSentTextView.setText(displayedText);
                    currentCharIndex++;
                } else {
                    Button ans1 = findViewById(R.id.ans1);
                    Button ans2 = findViewById(R.id.ans2);
                    Button ans3 = findViewById(R.id.ans3);
                    Button ans4 = findViewById(R.id.ans4);
                    ans1.setVisibility(View.VISIBLE);
                    ans2.setVisibility(View.VISIBLE);
                    ans3.setVisibility(View.VISIBLE);
                    ans4.setVisibility(View.VISIBLE);

                    ans1.setText(currentQuizData[1]);
                    ans2.setText(currentQuizData[2]);
                    ans3.setText(currentQuizData[3]);
                    ans4.setText(currentQuizData[4]);
                }
                handler.postDelayed(this, 100);
            }
        };

        // 初期化
        handler.post(timeRunnable);
        handler.post(textRunnable);

        // quiz_buttonを押したときの処理
        quiz_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(timeRunnable);
                handler.removeCallbacks(textRunnable);
                if (currentQuizIndex < quizDataList.size()) {
                    String[] currentQuizData = quizDataList.get(currentQuizIndex);
                    ans1.setVisibility(View.VISIBLE);
                    ans2.setVisibility(View.VISIBLE);
                    ans3.setVisibility(View.VISIBLE);
                    ans4.setVisibility(View.VISIBLE);

                    ans1.setText(currentQuizData[1]);
                    ans2.setText(currentQuizData[2]);
                    ans3.setText(currentQuizData[3]);
                    ans4.setText(currentQuizData[4]);

                    quiz_button.setEnabled(false);
                }
            }
        });

        // 各選択肢を押したときの処理
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

    private void nextQuestion() {
        currentQuestion++;

        if (currentQuizIndex < quizDataList.size() - 1) {
            currentQuizIndex++;
            currentCharIndex = 0;
        }

        if (currentQuestion > 10 || currentQuizIndex >= quizDataList.size()) {
            Intent intent = new Intent(MonadaiActivity.this, SyuuryouActivity.class);
            intent.putExtra("seconds", seconds);  // 経過時間を渡す
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(MonadaiActivity.this, MonadaiActivity.class);
            intent.putExtra("currentQuestion", currentQuestion);
            intent.putExtra("seconds", seconds);
            startActivity(intent);
            finish();
        }
    }
}
