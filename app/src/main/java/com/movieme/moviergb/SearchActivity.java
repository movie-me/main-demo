package com.movieme.moviergb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.movieme.moviergb.search.ListActivity;

public class SearchActivity extends AppCompatActivity {

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
                progressDialog = new ProgressDialog(SearchActivity.this);
                progressDialog.setMessage("영화를 검색중입니다. 잠시만 기다려주세요..");
                progressDialog.show();

                // 검색 액티비티로 전환
                Intent intent = new Intent(SearchActivity.this, ListActivity.class);

                // 검색 키워드 같이 전달
                String keyword = editText.getText().toString();
                intent.putExtra("keyword", keyword);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        // 다시 검색 화면으로 돌아왔을 때 프로그레스 바가 나타나지 않도록 처리
        progressDialog.dismiss();
    }

}
