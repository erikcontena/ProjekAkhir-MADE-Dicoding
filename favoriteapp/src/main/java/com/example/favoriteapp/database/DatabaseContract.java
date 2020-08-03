package com.example.favoriteapp.database;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    public static final String AUTHORITY = "com.example.projekakhir";
    private static final String SCHEME = "content";


    private DatabaseContract(){}

    public static final class MovieColumn implements BaseColumns {
        public static String TABLE_NAME = "table_movie";
        public static String TITLE = "title";
        public static String DATE = "date";
        public static String VOTE = "vote";
        public static String OVERVIEW = "overview";
        public static String JENIS = "jenis";
        public static String IMG = "img_film";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();

    }

}
