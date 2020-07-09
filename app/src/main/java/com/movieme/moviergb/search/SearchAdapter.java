package com.movieme.moviergb.search;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.movieme.moviergb.info.InfoActivity;
import com.movieme.moviergb.info.MovieInfoParser;
import com.movieme.moviergb.model.Movie;
import com.movieme.moviergb.R;
import com.movieme.moviergb.model.MovieInfo;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    Context context;
    private ArrayList<Movie> movies = null;
    private MovieInfo mMovieInfo = null;
    MovieInfoParser movieInfoParser;

    public SearchAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);

        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.mmovieNm.setText(movies.get(position).getMovieNm());
        holder.mopenDt.setText(movies.get(position).getOpenDt());
        holder.mnationAlt.setText(movies.get(position).getNationAlt());
        holder.mgenreAlt.setText(movies.get(position).getGenreAlt());
        holder.mtypeNm.setText(movies.get(position).getTypeNm());
        holder.mmovieCd.setText(movies.get(position).getMovieCd());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(SearchViewHolder searchViewHolder, View v, int position) {
                String gMovieCd = movies.get(position).getMovieCd();

                movieInfoParser = new MovieInfoParser(gMovieCd);
                movieInfoParser.execute(null, null, null);

                while (true) {
                    try {
                        Thread.sleep(1000);

                        // 멤버 변수에 담기
                        Log.d("aslkdjasldk", String.valueOf(movieInfoParser.target));
                        mMovieInfo = movieInfoParser.target;
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        String gMovieNm = mMovieInfo.getShowTm();

                        Intent intent = new Intent(context, InfoActivity.class);
                        intent.putExtra("showTm", gMovieNm);
                        intent.putExtra("movieCode", gMovieCd);

                        context.startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
