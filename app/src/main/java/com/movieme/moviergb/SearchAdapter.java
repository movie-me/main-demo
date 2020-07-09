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
    ArrayList<Model> models;

    public SearchAdapter(Context context, ArrayList<Model> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);

        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.mmovieNm.setText(models.get(position).getMovieNm());
        holder.mopenDt.setText(models.get(position).getOpenDt());
        holder.mnationAlt.setText(models.get(position).getNationAlt());
        holder.mgenreAlt.setText(models.get(position).getGenreAlt());
        holder.mtypeNm.setText(models.get(position).getTypeNm());
        holder.mmovieCd.setText(models.get(position).getMovieCd());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
