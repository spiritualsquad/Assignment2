package com.example.supreme.view;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.supreme.databinding.ActivityMainBinding;
import com.example.supreme.viewmodel.MovieViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        binding.searchButton.setOnClickListener(v -> {
            String query = binding.searchEditText.getText().toString().trim();
            if (!query.isEmpty()) {
                viewModel.searchMovies(query);
            }
        });

        viewModel.getMovies().observe(this, movies -> {
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
            binding.recyclerView.setAdapter(new MyAdapter(movies, movie -> {
                Intent intent = new Intent(this, MovieDetailsActivity.class);
                intent.putExtra("movieTitle", movie.getTitle());
                startActivity(intent);
            }));
        });
    }
}
