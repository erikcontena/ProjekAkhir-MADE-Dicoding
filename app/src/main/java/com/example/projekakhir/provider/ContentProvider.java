package com.example.projekakhir.provider;


import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projekakhir.database.MoviesHelper;

import static com.example.projekakhir.database.DatabaseContract.AUTHORITY;
import static com.example.projekakhir.database.DatabaseContract.MovieColumn.CONTENT_URI;
import static com.example.projekakhir.database.DatabaseContract.MovieColumn.TABLE_NAME;

public class ContentProvider extends android.content.ContentProvider {
    private static final int MOVIE = 1;

    private static final int MOVIE_ID = 2;


    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, TABLE_NAME, MOVIE);

        URI_MATCHER.addURI(AUTHORITY, TABLE_NAME + "/#", MOVIE_ID);

    }

    private MoviesHelper movieHelper;

    @Override
    public boolean onCreate() {
        movieHelper = new MoviesHelper(getContext());

        movieHelper.open();

        return true;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int updated;
        switch (URI_MATCHER.match(uri)) {
            case MOVIE_ID:

                updated = movieHelper.updateProvider(uri.getLastPathSegment(), values);

                break;
            default:

                updated = 0;

                break;
        }
        if (updated > 0) {

            getContext().getContentResolver().notifyChange(uri, null);
        }
        return updated;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        switch (URI_MATCHER.match(uri)) {
            case MOVIE:
                if (selection == null){

                    cursor = movieHelper.queryProvider();
                } else {
                    cursor = movieHelper.queryByNameProvider(selection);
                }
                break;
            case MOVIE_ID:
                cursor = movieHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:

                cursor = null;

                break;
        }
        if (cursor != null) {

            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        long added;
        switch (URI_MATCHER.match(uri)) {
            case MOVIE:
                added = movieHelper.insertProvider(values);
                break;
            default:
                added = 0;
                break;
        }
        if (added > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        int deleted;
        switch (URI_MATCHER.match(uri)) {
            case MOVIE_ID:
                deleted = movieHelper.deleteProvider(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }
        if (deleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return deleted;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

}