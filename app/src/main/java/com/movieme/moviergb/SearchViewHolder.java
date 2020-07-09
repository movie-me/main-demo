package com.movieme.moviergb;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView mmovieNm, mopenDt, mnationAlt, mgenreAlt, mtypeNm, mmovieCd;
    ItemClickListener itemClickListener;

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);

        this.mmovieNm = itemView.findViewById(R.id.movieNm);
        this.mopenDt = itemView.findViewById(R.id.openDt);
        this.mnationAlt = itemView.findViewById(R.id.nationAlt);
        this.mgenreAlt = itemView.findViewById(R.id.genreAlt);
        this.mtypeNm = itemView.findViewById(R.id.typeNm);
        this.mmovieCd = itemView.findViewById(R.id.movieCd);
    }

    @Override
    public void onClick(View view) {
        Log.d("asdkljaslkdjasldk", String.valueOf(getAdapterPosition()));
        this.itemClickListener.onItemClickListener(view, getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }
}
