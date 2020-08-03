package com.example.favoriteapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_FILM = "extra_film";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tvName = findViewById(R.id.tv_name_film);
        TextView tvRelase = findViewById(R.id.release2);
        TextView tvOverview = findViewById(R.id.overview2);
        TextView tvRating = findViewById(R.id.rating2);
        ImageView imageFilm = findViewById(R.id.img_film);

        Movie movie = getIntent().getParcelableExtra(EXTRA_FILM);
        String name = movie.getTitle();
        tvName.setText(name);
        collapsingToolbarLayout.setTitle(name);


        String relase = movie.getRelease_date();
        tvRelase.setText(relase);

        String overview = movie.getOverView();
        tvOverview.setText(overview);

        String  rating = movie.getVote_average();
        tvRating.setText(rating);

        RequestOptions requestOptions = new RequestOptions().centerCrop();

        Glide.with(this).load(movie.getImg_film()).apply(requestOptions).into(imageFilm);


    }
}
