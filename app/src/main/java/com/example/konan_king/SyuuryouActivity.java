package com.example.konan_king;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;

public class SyuuryouActivity extends AppCompatActivity {

    private TextView textView1;  // 経過時間を表示するTextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syuuryou);  // レイアウトを設定

        // textView1 を初期化
        textView1 = findViewById(R.id.textView1);  // TextViewのIDを確認

        // Intentから経過時間を受け取る
        Intent intent = getIntent();
        int seconds = intent.getIntExtra("seconds", 0);  // 経過時間（デフォルトは0）

        // 経過時間を表示
        textView1.setText("経過時間: " + seconds + "秒");
    }
}
