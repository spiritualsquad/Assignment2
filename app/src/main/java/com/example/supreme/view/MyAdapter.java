package com.example.supreme.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.supreme.databinding.MovieItemBinding;
import com.example.supreme.model.Movie;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private final List<Movie> movies;
    private final MyViewHolder.OnItemClickListener listener;

    public MyAdapter(List<Movie> movies, MyViewHolder.OnItemClickListener listener) {
        this.movies = movies;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemBinding binding = MovieItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(movies.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
