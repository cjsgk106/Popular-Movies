package com.example.andorid.popularmovie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.NumberViewHolder> {
    private ArrayList<Movie> movieList = new ArrayList<>();
    private final MovieInterface movieInterface;
    private static final String BASE_URL = "https://image.tmdb.org/t/p/w185";
    private Context context;

    MovieAdapter(MovieInterface movieInterface) {
        this.movieInterface = movieInterface;
    }

    public void setMovieList(ArrayList<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.grid_item, viewGroup, false);
        return new NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder numberViewHolder, int i) {
        String posterPath = movieList.get(i).getPoster_Path();
        Picasso.get().load(BASE_URL + posterPath).into(numberViewHolder.POSTERVIEW);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final ImageView POSTERVIEW;

        public NumberViewHolder(View itemView) {
            super(itemView);
            POSTERVIEW = (ImageView) itemView.findViewById(R.id.poster_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            movieInterface.startChildActivity(movieList.get(getAdapterPosition()));
        }
    }
}
