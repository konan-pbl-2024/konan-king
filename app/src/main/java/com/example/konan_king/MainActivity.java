package com.example.konan_king;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog; // ロード中のダイアログ

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.start_button);  //例: スタートボタン

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ロード画面を表示
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("読み込み中");
                progressDialog.setMessage("データを読み込んでいます...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();

                // 非同期で遷移
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // ロードが終わった後にMonadaiActivityに遷移
                        Intent intent = new Intent(MainActivity.this, MonadaiActivity.class);
                        startActivity(intent);

                        // ロード画面を閉じる
                        progressDialog.dismiss();
                    }
                }, 2000);  // 2秒後に遷移（ロード時間の模擬）
            }
        });
    }
}
