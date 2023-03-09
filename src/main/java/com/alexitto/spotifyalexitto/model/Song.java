package com.alexitto.spotifyalexitto.model;

public class Song {
    private String name;
    private String author;
    private String playlist;
    private int duration;
    private String image;

    public Song(String name, String author, String playlist, int duration, String image) {
        this.name = name;
        this.author = author;
        this.playlist = playlist;
        this.duration = duration;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
