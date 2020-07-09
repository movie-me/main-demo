package com.movieme.moviergb.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.movieme.moviergb.model.Movie;
import com.movieme.moviergb.R;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    // 멤버변수 선언
    RecyclerView mRecyclerView;
    SearchAdapter mAdapter;
    ArrayList<Movie> mMovies;

    MovieListParser movieListParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // MainActivity에서 키워드 값 받아오기
        Intent intent = getIntent();
        String keyword = intent.getExtras().getString("keyword");

        // 영화 목록 담을 클래스 추가
        movieListParser = new MovieListParser(keyword);

        // AsyncTask 실행
        // execute() 메서드에 의해 가장 먼저 호출되는 메서드가 onPreExecute()이고
        // 다음으로 자동으로 호출되는 메서드가 doInBackground()
        movieListParser.execute(null, null, null);

        while (true) {
            try {
                Thread.sleep(1000);

                // 멤버 변수에 담기
                mMovies = movieListParser.items;
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // RecyclerView를 위한 레이아웃 매니저 세팅
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // RecyclerView를 위한 어댑터 설정
        mAdapter = new SearchAdapter(this, mMovies);
        mRecyclerView.setAdapter(mAdapter);
    }

}
