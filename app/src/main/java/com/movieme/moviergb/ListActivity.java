package com.movieme.moviergb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

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

    private ArrayList<Movie> getMyList() {
        ArrayList<Movie> movies = new ArrayList<>();

        Movie m = new Movie();
        m.setMovieNm("아쿠아맨");
        m.setNationAlt("미국");
        m.setOpenDt("2018-12-19");
        m.setGenreAlt("판타지");
        m.setTypeNm("장편");
        m.setMovieCd("20180290");
        movies.add(m);

        m = new Movie();
        m.setMovieNm("보헤미안랩소디");
        m.setNationAlt("미국");
        m.setOpenDt("2018-10-31");
        m.setGenreAlt("감동");
        m.setTypeNm("장편");
        m.setMovieCd("20185485");
        movies.add(m);

        return movies;
    }

}
