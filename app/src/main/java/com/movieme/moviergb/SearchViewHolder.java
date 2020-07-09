package com.movieme.moviergb;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchViewHolder extends RecyclerView.ViewHolder {

    TextView mmovieNm, mopenDt, mnationAlt, mgenreAlt, mtypeNm, mmovieCd;

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);

        this.mmovieNm = itemView.findViewById(R.id.movieNm);
        this.mopenDt = itemView.findViewById(R.id.openDt);
        this.mnationAlt = itemView.findViewById(R.id.nationAlt);
        this.mgenreAlt = itemView.findViewById(R.id.genreAlt);
        this.mtypeNm = itemView.findViewById(R.id.typeNm);
        this.mmovieCd = itemView.findViewById(R.id.movieCd);
    }
}
