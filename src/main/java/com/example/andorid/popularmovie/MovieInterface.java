package com.example.andorid.popularmovie;

import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

public interface MovieInterface {
    String getSortBy();
    MovieAdapter getMovieAdapter();
    TextView getErrorMessage();
    ProgressBar getLoadingIndicator();
    RecyclerView getMovieGrid();
    void startChildActivity(Movie moive);
}
