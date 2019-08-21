package com.gmail.hmazud.submissionmovie2.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gmail.hmazud.submissionmovie2.adapter.MovieAdapter;
import com.gmail.hmazud.submissionmovie2.BuildConfig;
import com.gmail.hmazud.submissionmovie2.model.MovieModel;
import com.gmail.hmazud.submissionmovie2.model.Result;
import com.gmail.hmazud.submissionmovie2.network.ServiceMovie;
import com.gmail.hmazud.submissionmovie2.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowPlayingFragment extends Fragment {

    private Context context;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private MovieAdapter movieAdapter;

    private Call<Result> interfaceMovie;
    private ServiceMovie serviceMovie = new ServiceMovie();

    protected List<MovieModel> listMovie = new ArrayList<>();

    public NowPlayingFragment() {
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("listmovie",
                (ArrayList<? extends Parcelable>) listMovie);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);

        recyclerView = view.findViewById(R.id.rv_now_playing);
        progressBar = view.findViewById(R.id.progressBar);

        context = view.getContext();
        setupList();

        if (savedInstanceState != null) {
            List<MovieModel> movie = savedInstanceState.getParcelableArrayList("listmovie");
            listMovie.addAll(movie);
            progressBar.setVisibility(View.GONE);
        } else {
            interfaceMovie = serviceMovie.getService().getNowPlayingMovie(BuildConfig.API_KEY, "eng");
            interfaceMovie.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    listMovie.addAll(response.body().getResults());
                    progressBar.setVisibility(View.GONE);
                    movieAdapter.notifyDataSetChanged();
                }
                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    Toast.makeText(context, "Couldn't load Movies", Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, "Check your internet connection", Toast.LENGTH_LONG).show();
                }
            });
        }

        return view;
    }

    private void setupList() {
        movieAdapter = new MovieAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.replace(listMovie);
    }
}