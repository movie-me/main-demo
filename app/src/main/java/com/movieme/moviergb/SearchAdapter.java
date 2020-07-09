package com.movieme.moviergb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    Context context;
    private ArrayList<Movie> movies = null;

    public SearchAdapter(Context context, ArrayList<Movie> list) {
        this.context = context;
        this.movies = list;
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
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
