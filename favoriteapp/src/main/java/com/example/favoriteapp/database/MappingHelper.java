package com.example.favoriteapp.database;


import android.database.Cursor;

import com.example.favoriteapp.Movie;

import java.util.ArrayList;

public class MappingHelper {
    public static ArrayList<Movie> mapCursorToArrayList(Cursor mCursor) {
        ArrayList<Movie> moviesList = new ArrayList<>();
        while (mCursor.moveToNext()) {
            int id = mCursor.getInt(mCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn._ID));
            String title = mCursor.getString(mCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.TITLE));
            String overview = mCursor.getString(mCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.OVERVIEW));
            String img = mCursor.getString(mCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.IMG));
            String vote = mCursor.getString(mCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.VOTE));
            String jenis = mCursor.getString(mCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.JENIS));
            String release = mCursor.getString(mCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumn.DATE));

            moviesList.add(new Movie(title, vote, overview, jenis, img, id, release));
        }
//        Log.d("Movie List", String.valueOf(moviesList.size()));
        return moviesList;
    }

}
