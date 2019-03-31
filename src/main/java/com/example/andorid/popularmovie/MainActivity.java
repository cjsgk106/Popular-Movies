package com.example.andorid.popularmovie;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements MovieInterface {
    private RecyclerView movieGrid;
    private TextView errorMessage;
    private ProgressBar loadingIndicator;
    private String sortBy;
    private MovieAdapter movieAdapter;
    private static final String SORT_BY_POPULAR = "popular";
    private static final String SORT_BY_TOP_RATE = "top_rated";

    @Override
    public RecyclerView getMovieGrid() {
        return movieGrid;
    }

    @Override
    public String getSortBy() {
        return sortBy;
    }

    @Override
    public MovieAdapter getMovieAdapter() {
        return movieAdapter;
    }

    @Override
    public TextView getErrorMessage() {
        return errorMessage;
    }

    @Override
    public ProgressBar getLoadingIndicator() {
        return loadingIndicator;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieGrid = (RecyclerView) findViewById(R.id.movie_grid);
        errorMessage = (TextView) findViewById(R.id.error_message);
        loadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        movieGrid.setLayoutManager(gridLayoutManager);
        movieAdapter = new MovieAdapter(this);
        movieGrid.setAdapter(movieAdapter);
        sortBy = "popular";
        fetchMovie();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.sort_popularity) {
            sortBy = SORT_BY_POPULAR;
            fetchMovie();
            return true;
        }
        if (itemId == R.id.sort_rate) {
            sortBy = SORT_BY_TOP_RATE;
            fetchMovie();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startChildActivity(Movie movie) {
        Intent intent = new Intent(this, ChildActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }

    private void fetchMovie() {
        movieGrid.setVisibility(View.INVISIBLE);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if (info != null && info.isConnectedOrConnecting()) {
            errorMessage.setVisibility(View.INVISIBLE);
            loadingIndicator.setVisibility(View.VISIBLE);
            new FetchMovieTask().execute(this);
        } else {
            errorMessage.setVisibility(View.VISIBLE);
            loadingIndicator.setVisibility(View.INVISIBLE);
        }

    }
}
