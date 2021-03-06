package com.example.projekakhir.ui.notifications.Favorite;



import android.content.Intent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.projekakhir.DetailActivity;
import com.example.projekakhir.R;
import com.example.projekakhir.adapter.MovieAdapter;

import com.example.projekakhir.model.Movie;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements LoadMoviesCallback {


    private ProgressBar progressBar;
    private MovieAdapter adapter;
    private RecyclerView recyclerView;

    public MovieFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MovieAdapter();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_FILM,data);
                startActivity(intent);
            }
        });

    }

    @Override
    public void preExecute() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("preExecute","------------------------------");
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void postExecute(ArrayList<Movie> movies) {
        progressBar.setVisibility(View.GONE);
        if (movies.size() > 0) {
            adapter.setListMovie(movies);
            Log.d("postExecute", String.valueOf(movies.size()));

        } else {
            adapter.setListMovie(new ArrayList<Movie>());
            Log.d("postExecute", String.valueOf(adapter.getItemCount()));
            showSnackbarMessage("Tidak Ada Movie Favorite");

        }
    }



    private void showSnackbarMessage(String message) {
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        new LoadMoviesAsync(getActivity(), this, "movie").execute();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
