package com.example.supreme.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.supreme.utils.APIClient;
import com.example.supreme.model.Movie;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieViewModel extends ViewModel {
    private MutableLiveData<List<Movie>> movieList = new MutableLiveData<>();
    private MutableLiveData<Movie> selectedMovie = new MutableLiveData<>();

    public LiveData<List<Movie>> getMovies() {
        return movieList;
    }

    public LiveData<Movie> getSelectedMovie() {
        return selectedMovie;
    }

    public void searchMovies(String query) {
        APIClient.searchMovies(query, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) { e.printStackTrace(); }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonResponse.getJSONArray("Search");
                        List<Movie> movies = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject movieJson = jsonArray.getJSONObject(i);
                            movies.add(new Movie(
                                    movieJson.getString("Title"),
                                    movieJson.getString("Year"),
                                    movieJson.getString("imdbID"),
                                    movieJson.getString("Poster")
                            ));
                        }
                        movieList.postValue(movies);
                    } catch (Exception e) { e.printStackTrace(); }
                }
            }
        });
    }

    public void fetchMovieDetails(String title) {
        APIClient.getMovieDetails(title, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) { e.printStackTrace(); }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response.body().string());
                        Movie movie = new Movie(
                                jsonResponse.getString("Title"),
                                jsonResponse.getString("Year"),
                                jsonResponse.getString("imdbID"),
                                jsonResponse.getString("Poster"),
                                jsonResponse.getString("Plot"),
                                jsonResponse.getString("Director"),
                                jsonResponse.getString("Actors"),
                                jsonResponse.getString("imdbRating")

                        );
                        selectedMovie.postValue(movie);
                    } catch (Exception e) { e.printStackTrace(); }
                }
            }
        });
    }
}
