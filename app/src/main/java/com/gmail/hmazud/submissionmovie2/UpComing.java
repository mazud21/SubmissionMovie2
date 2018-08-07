package com.gmail.hmazud.submissionmovie2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.gmail.hmazud.submissionmovie2.Adapter.MovieAdapter;
import com.gmail.hmazud.submissionmovie2.Model.MovieModel;
import com.gmail.hmazud.submissionmovie2.NetworkService.InterfaceMovie;
import com.gmail.hmazud.submissionmovie2.NetworkService.ServiceMovie;

import java.util.List;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpComing extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_coming);

        progressBar = findViewById(R.id.progressBar);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        InterfaceMovie service = ServiceMovie.getRetrofitInstance().create(InterfaceMovie.class);
        retrofit2.Call<com.gmail.hmazud.submissionmovie2.Model.Result> call = service.getUpcomingMovie(BuildConfig.API_KEY, "id");
        call.enqueue(new Callback<com.gmail.hmazud.submissionmovie2.Model.Result>() {
            @Override
            public void onResponse(Call<com.gmail.hmazud.submissionmovie2.Model.Result> call, Response<com.gmail.hmazud.submissionmovie2.Model.Result> response) {
                generateMovieList(response.body().getResults());
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<com.gmail.hmazud.submissionmovie2.Model.Result> call, Throwable t) {

            }
        });
    }

    private void generateMovieList(List<MovieModel> listResults){
        recyclerView = findViewById(R.id.rv_up_coming);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MovieAdapter adapter = new MovieAdapter(listResults, this);
        recyclerView.setAdapter(adapter);
    }
}
