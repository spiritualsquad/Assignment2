package com.example.supreme.utils;

import okhttp3.*;
import java.io.IOException;

public class APIClient {
    private static final OkHttpClient client = new OkHttpClient();
    private static final String API_KEY = "26b579f";
    private static final String BASE_URL = "https://www.omdbapi.com/";

    public static void get(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }

    public static void searchMovies(String query, Callback callback) {
        String url = BASE_URL + "?s=" + query + "&apikey=" + API_KEY;
        get(url, callback);
    }

    public static void getMovieDetails(String title, Callback callback) {
        String url = BASE_URL + "?t=" + title + "&apikey=" + API_KEY;
        get(url, callback);
    }
}
