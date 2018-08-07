package com.gmail.hmazud.submissionmovie2;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gmail.hmazud.submissionmovie2.Model.MovieModel;
import com.google.gson.Gson;

public class DetailMovie extends AppCompatActivity {

    ImageView imageViewCover;
    ImageView imageViewPoster;
    TextView textViewDeskripsi;
    TextView textViewRilis;
    TextView textViewRating;
    TextView textViewJudul;
    MovieModel movieModel;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        imageViewCover = findViewById(R.id.image_cover);
        imageViewPoster = findViewById(R.id.image_poster);
        textViewDeskripsi = findViewById(R.id.text_deskripsi);
        textViewJudul = findViewById(R.id.text_judul);
        textViewRating = findViewById(R.id.text_rating);
        textViewRilis = findViewById(R.id.text_rilis);

        Gson gson = new Gson();
        String data = getIntent().getStringExtra("data");
        movieModel = gson.fromJson(data,MovieModel.class);

        Glide.with(this).load(BuildConfig.BASE_URL_IMG_500 +movieModel.getUrlImageCover()).into(imageViewCover);
        Glide.with(this).load(BuildConfig.BASE_URL_IMG_185 +movieModel.getUrlImagePoster()).into(imageViewPoster);

        textViewJudul.setText(movieModel.getTitle());
        textViewRilis.setText(movieModel.getRilis().substring(0,4));
        textViewRating.setText("Rating : " + movieModel.getRating());
        textViewDeskripsi.setText(movieModel.getOverview());
    }
}