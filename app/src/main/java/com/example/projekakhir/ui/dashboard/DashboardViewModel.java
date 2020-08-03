package com.example.projekakhir.ui.dashboard;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projekakhir.BuildConfig;
import com.example.projekakhir.model.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class DashboardViewModel extends ViewModel {


    private MutableLiveData<ArrayList<Movie>> listMovies = new MutableLiveData<>();


    void setMovies(String  search){
            String API_KEY = BuildConfig.TMDB_API_KEY;
            String url;
        if (search.equals("")){
                url = "https://api.themoviedb.org/3/discover/tv?api_key="+ API_KEY +"&language=en-US";
                Log.i("url", url);
            }else {
                url = "https://api.themoviedb.org/3/search/tv?api_key="+ API_KEY +"&language=en-US&query="+search;
                Log.i("url", url);
            }

            AsyncHttpClient client = new AsyncHttpClient();
            final ArrayList<Movie> listItems = new ArrayList<>();
            client.get(url, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        String result = new String(responseBody);
                        JSONObject responseObject = new JSONObject(result);
                        JSONArray list = responseObject.getJSONArray("results");

                        for (int i = 0; i < list.length(); i++) {
                            JSONObject film = list.getJSONObject(i);
                            Movie movie1 = new Movie();
                            movie1.setTitle(film.getString("name"));
                            movie1.setRelease_date(film.getString("first_air_date"));
                            movie1.setId(film.getInt("id"));
                            movie1.setOverView(film.getString("overview"));
                            String img = film.getString("poster_path");
                            movie1.setImg_film("https://image.tmdb.org/t/p/original/"+img);
                            double rating = film.getDouble("vote_average");
                            movie1.setVote_average(new DecimalFormat("#.#").format(rating));
                            movie1.setJenis("tv");
                            listItems.add(movie1);

                        }
                        listMovies.postValue(listItems);
                    } catch (Exception e) {
                        Log.d("Exception", e.getMessage());
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Log.d("onFailure", error.getMessage());
                }
            });

        }

        LiveData<ArrayList<Movie>> getMovies(){
            return listMovies;
        }
    }