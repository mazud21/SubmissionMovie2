package com.gmail.hmazud.submissionmovie2;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gmail.hmazud.submissionmovie2.Model.MovieModel;

public class DetailMovie extends AppCompatActivity {

    ImageView imageViewCover;
    ImageView imageViewPoster;
    TextView textViewDeskripsi;
    TextView textViewRilis;
    TextView textViewRating;
    TextView textViewJudul;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        MovieModel movieModel = getIntent().getParcelableExtra("data");

        String judul = movieModel.getTitle();
        String deskripsi = movieModel.getOverview();
        String rating = movieModel.getRating();
        String rilis = movieModel.getRilis();

        imageViewCover = findViewById(R.id.image_cover);
        imageViewPoster = findViewById(R.id.image_poster);

        Glide.with(this).load(BuildConfig.BASE_URL_IMG_185+movieModel.getUrlImagePoster()).into(imageViewPoster);
        Glide.with(this).load(BuildConfig.BASE_URL_IMG_500+movieModel.getUrlImagePoster()).into(imageViewCover);

        textViewJudul = findViewById(R.id.text_judul);
        textViewDeskripsi = findViewById(R.id.text_deskripsi);
        textViewRating = findViewById(R.id.text_rating);
        textViewRilis = findViewById(R.id.text_rilis);

        textViewJudul.setText(judul);
        textViewDeskripsi.setText(deskripsi);
        textViewRating.setText("Rating : "+rating);
        textViewRilis.setText(rilis);

    }
}