package com.movieme.moviergb;

import android.view.View;

import com.movieme.moviergb.search.SearchViewHolder;

public interface ItemClickListener {
    void onItemClickListener(SearchViewHolder searchViewHolder, View v, int position);
}
