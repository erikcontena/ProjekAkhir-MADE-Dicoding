package com.example.projekakhir.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.MediaStore.Audio.Playlists.Members._ID;
import static com.example.projekakhir.database.DatabaseContract.MovieColumn.DATE;
import static com.example.projekakhir.database.DatabaseContract.MovieColumn.IMG;
import static com.example.projekakhir.database.DatabaseContract.MovieColumn.JENIS;
import static com.example.projekakhir.database.DatabaseContract.MovieColumn.OVERVIEW;
import static com.example.projekakhir.database.DatabaseContract.MovieColumn.TABLE_NAME;
import static com.example.projekakhir.database.DatabaseContract.MovieColumn.TITLE;
import static com.example.projekakhir.database.DatabaseContract.MovieColumn.VOTE;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "dbmovies";


    private static final int DATABASE_VERSION = 1;

    private static String CREATE_TABLE_MOVIE = "create table " + TABLE_NAME +
            " (" + _ID + " integer primary key, " +
            TITLE + " text not null, " +
            DATE + " text not null, " +
            VOTE + " text not null, " +
            OVERVIEW + " text not null, "+
            JENIS + " text not null, " +
            IMG + " text not null );";


    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
