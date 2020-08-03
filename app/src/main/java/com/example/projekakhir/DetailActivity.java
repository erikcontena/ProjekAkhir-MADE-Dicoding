package com.example.projekakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.projekakhir.model.Movie;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import static android.provider.MediaStore.Audio.Playlists.Members._ID;
import static com.example.projekakhir.database.DatabaseContract.MovieColumn.CONTENT_URI;
import static com.example.projekakhir.database.DatabaseContract.MovieColumn.DATE;
import static com.example.projekakhir.database.DatabaseContract.MovieColumn.IMG;
import static com.example.projekakhir.database.DatabaseContract.MovieColumn.JENIS;
import static com.example.projekakhir.database.DatabaseContract.MovieColumn.OVERVIEW;
import static com.example.projekakhir.database.DatabaseContract.MovieColumn.TITLE;
import static com.example.projekakhir.database.DatabaseContract.MovieColumn.VOTE;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_FILM = "extra_film";

    private Uri uriWithId;
    private Cursor cursor;

    private int id;
    private String name;
    private String relase;
    private String overview;
    private String rating;
    private String img;
    private String jenis;

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
        id = movie.getId();
        jenis = movie.getJenis();

        name = movie.getTitle();
        tvName.setText(name);
        collapsingToolbarLayout.setTitle(name);


        relase = movie.getRelease_date();
        tvRelase.setText(relase);

        overview = movie.getOverView();
        tvOverview.setText(overview);

        rating = movie.getVote_average();
        tvRating.setText(rating);

        RequestOptions requestOptions = new RequestOptions().centerCrop();

        img = movie.getImg_film();
        Glide.with(this).load(img).apply(requestOptions).into(imageFilm);
        Button btnFavorite = findViewById(R.id.btn_favorite);

        uriWithId = Uri.parse(CONTENT_URI + "/" + movie.getId());
        if (uriWithId != null) {
            cursor = getContentResolver().query(uriWithId, null, null, null, null);
            Log.d("cursor", String.valueOf(cursor.getColumnIndexOrThrow(_ID)));
            if (cursor.getCount() == 0 ) {
                btnFavorite.setText(getString(R.string.add_favorite));
                cursor.close();
            }else {
                btnFavorite.setText(getString(R.string.delete_favorite));
            }
        }


        btnFavorite.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_favorite){

            if (cursor.getCount() == 0){
                ContentValues contentValues = new ContentValues();
                contentValues.put(_ID,id);
                contentValues.put(TITLE,name);
                contentValues.put(OVERVIEW, overview);
                contentValues.put(VOTE, rating);
                contentValues.put(IMG, img);
                contentValues.put(DATE, relase);
                contentValues.put(JENIS, jenis);
                getContentResolver().insert(CONTENT_URI, contentValues);
                Toast.makeText(DetailActivity.this, getString(R.string.add_favorite) +" "+ name , Toast.LENGTH_SHORT).show();
                finish();

            }else {
                getContentResolver().delete(uriWithId, null, null);
                Toast.makeText(DetailActivity.this, getString(R.string.delete_favorite) +" "+ name, Toast.LENGTH_SHORT).show();
                finish();

            }

            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }
}
