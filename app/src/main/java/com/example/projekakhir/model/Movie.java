package com.example.projekakhir.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    public Movie(String title, String vote_average, String overView, String jenis, String img_film, int id, String release) {
        this.title = title;
        this.vote_average = vote_average;
        this.overView = overView;
        this.jenis = jenis;
        this.img_film = img_film;
        this.release_date = release;
        this.id = id;
    }

    public Movie() {

    }

    private String title;
    private String release_date;
    private String vote_average;
    private String overView;
    private String jenis;
    private String img_film;

    public Movie(Parcel in) {
        title = in.readString();
        release_date = in.readString();
        vote_average = in.readString();
        overView = in.readString();
        jenis = in.readString();
        img_film = in.readString();
        id = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;




    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public String getImg_film() {
        return img_film;
    }

    public void setImg_film(String img_film) {
        this.img_film = img_film;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(release_date);
        dest.writeString(vote_average);
        dest.writeString(overView);
        dest.writeString(jenis);
        dest.writeString(img_film);
        dest.writeInt(id);
    }
}

