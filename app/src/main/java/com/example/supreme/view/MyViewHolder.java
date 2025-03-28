package com.example.supreme.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.example.supreme.databinding.MovieItemBinding;
import com.example.supreme.model.Movie;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyViewHolder extends RecyclerView.ViewHolder {
    private final MovieItemBinding binding;

    public MyViewHolder(MovieItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Movie movie, OnItemClickListener listener) {
        binding.movieTitle.setText(movie.getTitle());
        binding.movieYear.setText(movie.getYear());
        binding.movieRating.setText(movie.getImdbRating());


        new ImageLoadTask(movie.getPoster(), binding.moviePoster).execute();

        itemView.setOnClickListener(v -> listener.onItemClick(movie));
    }

    public interface OnItemClickListener {
        void onItemClick(Movie movie);
    }


    static class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {
        private final String url;
        private final android.widget.ImageView imageView;

        public ImageLoadTask(String url, android.widget.ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                return BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                imageView.setImageBitmap(result);
            }
        }
    }
}
