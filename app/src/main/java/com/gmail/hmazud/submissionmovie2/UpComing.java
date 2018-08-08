package com.gmail.hmazud.submissionmovie2;

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

public class UpComing extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_coming);

        progressBar = findViewById(R.id.progressBar);

        InterfaceMovie interfaceMovie = ServiceMovie.getRetrofitInstance().create(InterfaceMovie.class);
        retrofit2.Call<Result> resultCall = interfaceMovie.getUpcomingMovie(BuildConfig.API_KEY, "eng");

        resultCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                generateMovieList(response.body().getResults());
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }

    private void generateMovieList(List<MovieModel> listResults) {
        recyclerView = findViewById(R.id.rv_up_coming);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        MovieAdapter movieAdapter = new MovieAdapter(listResults, this);
        recyclerView.setAdapter(movieAdapter);
    }
}
