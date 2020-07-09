package com.movieme.moviergb;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    TextView mMovieNameTv, mOpenDateTv, mNationTv, mGenreTv, mTypeNameTv, mMovieCodeTv, mShowTmTv, mDirectorTv, mActorTv, mCompanyTv, mAuditTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ActionBar actionBar = getSupportActionBar();

        mMovieNameTv = findViewById(R.id.movieName);
        mOpenDateTv = findViewById(R.id.openDate);
        mNationTv = findViewById(R.id.nation);
        mGenreTv = findViewById(R.id.genre);
        mTypeNameTv = findViewById(R.id.typeName);
        mMovieCodeTv = findViewById(R.id.movieCode);
        mShowTmTv = findViewById(R.id.showTm);
        mDirectorTv = findViewById(R.id.director);
        mActorTv = findViewById(R.id.actor);
        mCompanyTv = findViewById(R.id.company);
        mAuditTv = findViewById(R.id.audit);

        Intent intent = getIntent();

        String mMovieName = intent.getStringExtra("iMovieNm");
        String mOpenDate = intent.getStringExtra("iOpenDt");
        String mNation = intent.getStringExtra("iNationAlt");
        String mGenre = intent.getStringExtra("iGenreAlt");
        String mTypeName = intent.getStringExtra("iTypeNm");
        String mMovieCode = intent.getStringExtra("iMovieCd");

        actionBar.setTitle(mMovieName);

        mMovieNameTv.setText(mMovieName);
        mOpenDateTv.setText(mOpenDate);
        mNationTv.setText(mNation);
        mGenreTv.setText(mGenre);
        mTypeNameTv.setText(mTypeName);
        mMovieCodeTv.setText(mMovieCode);

        //주석 처리한 부분으로 InfoActivity 내용 수정 가능
        //mShowTmTv.setText("180");
        //mDirectorTv.setText("크리스토퍼 놀란");
        //mActorTv.setText("로버트 다우니 주니어");
        //mCompanyTv.setText("CJ");
        //mAuditTv.setText("15");

    }

}
