package com.example.supreme.model;

public class Movie {
    private String title;
    private String year;
    private String imdbID;
    private String poster;
    private String plot;
    private String director;
    private String actors;
    private String imdbRating;

    public Movie(String title, String year, String imdbID, String poster) {
        this.title = title;
        this.year = year;
        this.imdbID = imdbID;
        this.poster = poster;
    }

    public Movie(String title, String year, String imdbID, String poster, String plot, String director, String actors, String imdbRating) {
        this.title = title;
        this.year = year;
        this.imdbID = imdbID;
        this.poster = poster;
        this.plot = plot;
        this.director = director;
        this.actors = actors;
        this.imdbRating = imdbRating;
    }

    public String getTitle() { return title; }
    public String getYear() { return year; }
    public String getImdbID() { return imdbID; }
    public String getPoster() { return poster; }
    public String getPlot() { return plot; }
    public String getDirector() { return director; }
    public String getActors() { return actors; }
    public String getImdbRating() { return imdbRating; }
}
