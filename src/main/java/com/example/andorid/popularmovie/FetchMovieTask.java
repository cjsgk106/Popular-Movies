package com.example.andorid.popularmovie;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class FetchMovieTask extends AsyncTask<MovieInterface, Void, ArrayList<Movie>> {
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static final String API_KEY = "?api_key=" + ""; // fill the API key in the second
    private MovieInterface movieInterface;
    @Override
    protected ArrayList<Movie> doInBackground(MovieInterface... movieInterfaces) {
        movieInterface = movieInterfaces[0];
        ArrayList<Movie> movies = new ArrayList<>();
        URL url = null;
        try {
            url = new URL(BASE_URL + movieInterface.getSortBy() + API_KEY);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            try {
                InputStream jsonStream = connection.getInputStream();
                Scanner scanner = new Scanner(jsonStream);
                scanner.useDelimiter("\\A");
                if (scanner.hasNext()) {
                    JSONObject json = new JSONObject(scanner.next());
                    JSONArray results = json.getJSONArray("results");
                    for (int i = 0; i < results.length(); ++i) {
                        JSONObject result = results.getJSONObject(i);
                        String title = result.getString("title");
                        String releaseDate = result.getString("release_date");
                        String posterPath = result.getString("poster_path");
                        String rate = result.getString("vote_average");
                        String description = result.getString("overview");
                        movies.add(new Movie(title, releaseDate, rate, posterPath, description));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                connection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movies) {
        super.onPostExecute(movies);
        RecyclerView movieGrid = movieInterface.getMovieGrid();
        movieInterface.getMovieAdapter().setMovieList(movies);
        movieGrid.setVisibility(View.VISIBLE);
    }
}
