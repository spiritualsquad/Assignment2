package com.example.supreme.view;

import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.supreme.databinding.ActivityMovieDetailsBinding;
import com.example.supreme.viewmodel.MovieViewModel;

public class MovieDetailsActivity extends AppCompatActivity {
    private ActivityMovieDetailsBinding binding;
    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        String movieTitle = getIntent().getStringExtra("movieTitle");

        viewModel.fetchMovieDetails(movieTitle);
        viewModel.getSelectedMovie().observe(this, movie -> {
            new MyViewHolder.ImageLoadTask(movie.getPoster(), binding.moviePoster).execute();

            binding.movieTitle.setText(movie.getTitle());
            binding.movieInfo.setText(movie.getYear() + " • " + movie.getImdbRating());
            binding.movieRating.setText(movie.getImdbRating());
            binding.moviePlot.setText(movie.getPlot());
        });

        binding.backButton.setOnClickListener(v -> finish());
    }
}
