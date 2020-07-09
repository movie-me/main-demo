package com.movieme.moviergb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // 멤버변수 설정
    EditText editText;
    Button button;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 멤버변수 초기화
        editText = findViewById(R.id.search_edittext);
        button = findViewById(R.id.search_btn);

        // 검색 버튼 클릭 시
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 프로그레스 바 생성
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("영화를 검색중입니다. 잠시만 기다려주세요..");
                progressDialog.show();

                String keyword = editText.getText().toString();
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("keyword", keyword);
                startActivity(intent);
            }
        });
    }
}
