package com.gmail.hmazud.submissionmovie2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class SearchMovie extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        final ProgressBar progressBar = findViewById(R.id.progressBar);
        final EditText editText = findViewById(R.id.et_search);
        Button button = findViewById(R.id.btn_search);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_search:
                        progressBar.setVisibility(View.VISIBLE);
                        String s = editText.getText().toString();
                        if (TextUtils.isEmpty(s)) {
                            editText.setError("Can't Empty !");
                        } else {
                            InterfaceMovie interfaceMovie = ServiceMovie.getRetrofitInstance().create(InterfaceMovie.class);
                            retrofit2.Call<Result> resultCall = interfaceMovie.searchMovie(BuildConfig.API_KEY, "eng",s);

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
                }
            }
        });
    }

    private void generateMovieList(List<MovieModel> listResults) {
        recyclerView = findViewById(R.id.rv_search);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        MovieAdapter movieAdapter = new MovieAdapter(listResults, this);
        recyclerView.setAdapter(movieAdapter);
    }
}