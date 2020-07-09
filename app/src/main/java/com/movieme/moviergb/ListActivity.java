package com.movieme.moviergb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchAdapter = new SearchAdapter(this, getMyList());
        mRecyclerView.setAdapter(searchAdapter);
    }

    private ArrayList<Model> getMyList() {
        ArrayList<Model> models = new ArrayList<>();

        Model m = new Model();
        m.setMovieNm("아쿠아맨");
        m.setNationAlt("미국");
        m.setOpenDt("2018-12-19");
        m.setGenreAlt("판타지");
        m.setTypeNm("장편");
        m.setMovieCd("20180290");
        models.add(m);

        m = new Model();
        m.setMovieNm("보헤미안랩소디");
        m.setNationAlt("미국");
        m.setOpenDt("2018-10-31");
        m.setGenreAlt("감동");
        m.setTypeNm("장편");
        m.setMovieCd("20185485");
        models.add(m);

        return models;
    }

}
