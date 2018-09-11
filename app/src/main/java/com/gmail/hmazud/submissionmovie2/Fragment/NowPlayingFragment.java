package com.gmail.hmazud.submissionmovie2.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gmail.hmazud.submissionmovie2.Adapter.MovieAdapter;
import com.gmail.hmazud.submissionmovie2.BuildConfig;
import com.gmail.hmazud.submissionmovie2.Model.MovieModel;
import com.gmail.hmazud.submissionmovie2.Model.Result;
import com.gmail.hmazud.submissionmovie2.NetworkService.InterfaceMovie;
import com.gmail.hmazud.submissionmovie2.NetworkService.ServiceMovie;
import com.gmail.hmazud.submissionmovie2.R;

import java.util.List;

import butterknife.Unbinder;
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

    public NowPlayingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);

        recyclerView = view.findViewById(R.id.rv_now_playing);
        progressBar = view.findViewById(R.id.progressBar);

        context = view.getContext();

        setupList();
        loadData();

        return view;
    }

    private void loadData() {
        interfaceMovie = serviceMovie.getService().getNowPlayingMovie(BuildConfig.API_KEY,"eng");
        interfaceMovie.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                    movieAdapter.replace(response.body().getResults());
                    progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(context, "Couldn't load Movies", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupList() {
        movieAdapter = new MovieAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(movieAdapter);
    }
}