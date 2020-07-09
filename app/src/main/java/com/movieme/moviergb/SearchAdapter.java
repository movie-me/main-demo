package com.movieme.moviergb;

import android.content.Context;
import android.content.Intent;
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

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                String gMovieNm = movies.get(position).getMovieNm();
                String gOpenDt = movies.get(position).getOpenDt();
                String gNationAlt = movies.get(position).getNationAlt();
                String gGenreAlt = movies.get(position).getGenreAlt();
                String gTypeNm = movies.get(position).getTypeNm();
                String gMovieCd = movies.get(position).getMovieCd();

                Intent intent = new Intent(context, InfoActivity.class);
                intent.putExtra("iMovieNm", gMovieNm);
                intent.putExtra("iOpenDt", gOpenDt);
                intent.putExtra("iNationAlt", gNationAlt);
                intent.putExtra("iGenreAlt", gGenreAlt);
                intent.putExtra("iTypeNm", gTypeNm);
                intent.putExtra("iMovieCd", gMovieCd);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
