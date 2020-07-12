package com.movieme.moviergb.info;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.movieme.moviergb.R;

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
        mCompanyTv = findViewById(R.id.company);
        mAuditTv = findViewById(R.id.audit);

        Intent intent = getIntent();
        String movieCode = intent.getExtras().getString("movieCode");
        Log.d("[InfoActivity][movieCd]", movieCode);

        String mMovieName = intent.getStringExtra("movieName");
        String mOpenDate = intent.getStringExtra("openDate");
        String mNation = intent.getStringExtra("nation");
        String mGenre = intent.getStringExtra("genre");
        String mTypeName = intent.getStringExtra("typeName");
        // String mMovieCode = intent.getStringExtra("iMovieCd");
        String showTm = intent.getStringExtra("showTm");
        String director = intent.getStringExtra("director");
        String company = intent.getStringExtra("company");
        String audit = intent.getStringExtra("audit");

        Log.d("[InfoActivity][showTm]", showTm);

        actionBar.setTitle(mMovieName);

        mMovieNameTv.setText(mMovieName);
        mOpenDateTv.setText(mOpenDate);
        mNationTv.setText(mNation);
        mGenreTv.setText(mGenre);
        mTypeNameTv.setText(mTypeName);
        // mMovieCodeTv.setText(mMovieCode);
        mShowTmTv.setText(showTm);
        mDirectorTv.setText(director);
        mCompanyTv.setText(company);
        mAuditTv.setText(audit);

        // 주석 처리한 부분으로 InfoActivity 내용 수정 가능
        // mShowTmTv.setText("180");
        // mDirectorTv.setText("크리스토퍼 놀란");
        // mActorTv.setText("로버트 다우니 주니어");
        // mCompanyTv.setText("CJ");
        // mAuditTv.setText("15");
    }

}
