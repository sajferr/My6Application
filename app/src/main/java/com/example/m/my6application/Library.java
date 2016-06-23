package com.example.m.my6application;

/**
 * Created by m on 2016-06-23.
 */
public class Library {

//                "title": "Dawn of the Planet of the Apes",
//                        "image": "http://api.androidhive.info/json/movies/1.jpg",
//                        "rating": 8.3,
//                        "releaseYear": 2014,
//                        "genre": ["Action", "Drama", "Sci-Fi"]

    private   String title ;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    private float rating;
    private int releaseYear;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
