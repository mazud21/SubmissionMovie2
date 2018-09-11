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
import android.widget.Toast;

import com.gmail.hmazud.submissionmovie2.Adapter.MovieAdapter;
import com.gmail.hmazud.submissionmovie2.Model.MovieModel;
import com.gmail.hmazud.submissionmovie2.Model.Result;
import com.gmail.hmazud.submissionmovie2.NetworkService.InterfaceMovie;
import com.gmail.hmazud.submissionmovie2.NetworkService.ServiceMovie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMovie extends AppCompatActivity {

    public static final String MOVIE_TITLE = "movie_title";

    EditText editText;
    ProgressBar progressBar;
    Button button;
    RecyclerView recyclerView;

    private MovieAdapter movieAdapter;

    private Call<Result> interfaceMovie;
    private ServiceMovie serviceMovie = new ServiceMovie();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        editText = findViewById(R.id.et_search);
        progressBar = findViewById(R.id.progressBar);
        button = findViewById(R.id.btn_search);
        recyclerView = findViewById(R.id.rv_search);

        setupList();

        //final String movie_title = getIntent().getStringExtra(MOVIE_TITLE);
        //loadData(movie_title);

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
                            loadData(s);
                        }
                }
            }
        });

        //setupList();
    }

    private void setupList() {
        movieAdapter = new MovieAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieAdapter);
    }

    private void loadData(String s) {
        interfaceMovie = serviceMovie.getService().getSearchMovie(BuildConfig.API_KEY,"eng",s);
        interfaceMovie.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                movieAdapter.replace(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }
}
