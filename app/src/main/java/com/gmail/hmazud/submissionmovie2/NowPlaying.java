package com.gmail.hmazud.submissionmovie2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.gmail.hmazud.submissionmovie2.Adapter.MovieAdapter;
import com.gmail.hmazud.submissionmovie2.Model.MovieModel;
import com.gmail.hmazud.submissionmovie2.Model.Result;
import com.gmail.hmazud.submissionmovie2.NetworkService.InterfaceMovie;
import com.gmail.hmazud.submissionmovie2.NetworkService.ServiceMovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowPlaying extends AppCompatActivity {

    private Context context;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private MovieAdapter movieAdapter;

    private Call<Result> interfaceMovie;
    private ServiceMovie serviceMovie = new ServiceMovie();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        recyclerView = findViewById(R.id.rv_now_playing);
        progressBar = findViewById(R.id.progressBar);

        setupList();

        interfaceMovie = serviceMovie.getService().getNowPlayingMovie(BuildConfig.API_KEY,"eng");
        interfaceMovie.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                movieAdapter.replace(response.body().getResults());
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }

    private void setupList() {
        movieAdapter = new MovieAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(movieAdapter);
    }
}
