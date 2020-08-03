package com.example.projekakhir.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;

import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;

import com.bumptech.glide.request.target.Target;
import com.example.projekakhir.R;
import com.example.projekakhir.database.MappingHelper;
import com.example.projekakhir.model.Movie;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.example.projekakhir.database.DatabaseContract.MovieColumn.CONTENT_URI;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {


    private Context context;
    private ArrayList<Movie> movies = new ArrayList<>();
    private Cursor cursor;

    public StackRemoteViewsFactory(Context context) {
        this.context = context;

    }

    @Override
    public void onCreate() {
        cursor = context.getContentResolver().query(CONTENT_URI, null, null, null, null);
        movies = MappingHelper.mapCursorToArrayList(cursor);
        Log.d("Oncreate", String.valueOf(movies.size()));
    }

    @Override
    public void onDataSetChanged() {

        final long identityToken = Binder.clearCallingIdentity();

        cursor = context.getContentResolver().query(CONTENT_URI, null, null, null, null);
        Log.d("dataChange", String.valueOf(cursor.getColumnCount()));


        Binder.restoreCallingIdentity(identityToken);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_item);

        Log.d("getView", String.valueOf(movies.size()));

        Bitmap bmp = null;
        try {
            //noinspection deprecation
            bmp = Glide.with(context)
                    .asBitmap()
                    .load(movies.get(position).getImg_film())
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
            rv.setImageViewBitmap(R.id.imageView, bmp);
            rv.setTextViewText(R.id.banner_text, movies.get(position).getTitle());
            Log.d("Widget", "Success");
        } catch (InterruptedException | ExecutionException e) {
            Log.d("Widget Load Error", "Error");
        }
        Bundle extras = new Bundle();
        extras.putInt(FavoriteWidget.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


}
