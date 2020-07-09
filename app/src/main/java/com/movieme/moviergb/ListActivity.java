package com.movieme.moviergb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    static final String API_KEY = "84860e7bae0b07af4c7c7ff379be1997";
    public static final int LOAD_SUCCESS = 101;

    RecyclerView mRecyclerView;
    SearchAdapter mAdapter;
    ArrayList<Movie> mMovies;

    MovieList movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // MainActivity에서 키워드 값 받아오기
        Intent intent = getIntent();
        String keyword = intent.getExtras().getString("keyword");

        movieList = new MovieList(keyword);
        movieList.execute(null, null, null);

        // doInBackground 메서드 호출

        // execute() :
        // AsyncTask를 실행시킨다. execute() 메서드에 의해 가장 먼저 호출되는 메서드가
        // onPreExecute()이고 다음으로 자동으로 호출되는 메서드가 doInBackground() 이다.
        while (true) {
            try {
                Thread.sleep(1000);

                if (movieList.flag == true) {
                    mMovies = movieList.items;
                    break;
                }
            } catch (Exception e) {
            }
        }   // end of while loop

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new SearchAdapter(this, mMovies);
        mRecyclerView.setAdapter(mAdapter);
    }

}
