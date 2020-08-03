package com.example.projekakhir.Reminder;

import android.content.Context;
import android.util.Log;

import com.example.projekakhir.BuildConfig;
import com.example.projekakhir.model.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class ReleaseApi {
    public ReleaseApi(final Context appContext) {
        Log.d(this.getClass().getSimpleName(), "ReleaseTodayRepository: EXEC");

        final ArrayList<Movie> itemsList = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + BuildConfig.TMDB_API_KEY + "&primary_release_date.gte=" + getCurrentDate() + "&primary_release_date.lte=" + getCurrentDate();
//        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + BuildConfig.TMDB_API_KEY + "&primary_release_date.gte=2019-01-31&primary_release_date.lte=2019-01-31";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    Log.d("Movie release ", String.valueOf(list.length()));

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject film = list.getJSONObject(i);
                        Movie movie1 = new Movie();
                        movie1.setTitle(film.getString("title"));
                        movie1.setRelease_date(film.getString("release_date"));
                        movie1.setId(film.getInt("id"));
                        movie1.setOverView(film.getString("overview"));
                        String img = film.getString("poster_path");
                        movie1.setImg_film("https://image.tmdb.org/t/p/original/"+img);
                        double rating = film.getDouble("vote_average");
                        movie1.setVote_average(new DecimalFormat("#.#").format(rating));
                        movie1.setJenis("movie");
                        itemsList.add(movie1);

                    }
                    Log.d("Movie item list ", String.valueOf(itemsList.size()));

                    AlarmNotification.onReceiveReleaseToday(appContext, itemsList);
                } catch (JSONException e) {
                    Log.d("JSONException", Objects.requireNonNull(e.getMessage()));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", Objects.requireNonNull(error.getMessage()));
            }
        });
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
