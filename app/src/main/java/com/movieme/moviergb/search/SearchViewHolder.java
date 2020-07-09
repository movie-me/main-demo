package com.movieme.moviergb.search;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.movieme.moviergb.R;

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

        // 영화 목록 중 한 row 클릭 시 상세 영화 정보 화면으로 이동하는 이벤트
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // RecyclerView 아이템의 위치를 가져옴
                int position = getAdapterPosition();
                Log.d("[SearchViewHolder][Pos]", String.valueOf(position));

                if (itemClickListener != null) {
                    itemClickListener.onItemClickListener(SearchViewHolder.this, v, position);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClickListener(SearchViewHolder.this, view, getLayoutPosition());
    }

    // 아이템 클릭 이벤트 리스너 설정
    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }

}
