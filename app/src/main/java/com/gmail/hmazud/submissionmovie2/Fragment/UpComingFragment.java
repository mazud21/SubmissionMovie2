package com.gmail.hmazud.submissionmovie2.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.gmail.hmazud.submissionmovie2.Adapter.MovieAdapter;
import com.gmail.hmazud.submissionmovie2.BuildConfig;
import com.gmail.hmazud.submissionmovie2.Model.MovieModel;
import com.gmail.hmazud.submissionmovie2.Model.Result;
import com.gmail.hmazud.submissionmovie2.NetworkService.InterfaceMovie;
import com.gmail.hmazud.submissionmovie2.NetworkService.ServiceMovie;
import com.gmail.hmazud.submissionmovie2.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpComingFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public UpComingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_up_coming, container, false);

        progressBar = view.findViewById(R.id.progressBar);

        InterfaceMovie interfaceMovie = ServiceMovie.getRetrofitInstance().create(InterfaceMovie.class);
        retrofit2.Call<Result> resultCall = interfaceMovie.getUpcomingMovie(BuildConfig.API_KEY, "id");

        resultCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                generateMovieList(response.body().getResults(),view);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });

        return view;
    }

    private void generateMovieList(List<MovieModel> listResults, View view) {
        recyclerView = view.findViewById(R.id.rv_up_coming);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        MovieAdapter movieAdapter = new MovieAdapter(listResults, getContext());
        recyclerView.setAdapter(movieAdapter);
    }
}
