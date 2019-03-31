package com.example.andorid.popularmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ChildActivity extends AppCompatActivity {
    private static final String BASE_URL =  "https://image.tmdb.org/t/p/w185";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("movie");
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(movie.getTitle());
        ImageView poster = (ImageView) findViewById(R.id.poster);
        String posterPath = movie.getPoster_Path();

        Picasso.get().load(BASE_URL + posterPath).into(poster);
        TextView releaseDate = (TextView) findViewById(R.id.date_released);
        String dateReleased = movie.getDate_Released();
        releaseDate.setText(dateReleased);

        TextView rate = (TextView) findViewById(R.id.vote_average);

        String rateStr = movie.getRate();
        String rateText = getString(R.string.vote_average, rateStr);
        rate.setText(rateText);

        TextView description = (TextView) findViewById(R.id.overview);
        description.setText(movie.getDescription());
    }
}
