package com.gmail.hmazud.submissionmovie2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gmail.hmazud.submissionmovie2.model.MovieModel;
import com.gmail.hmazud.submissionmovie2.database.FavoriteHelper;
import com.gmail.hmazud.submissionmovie2.provider.FavoriteColumns;

import static com.gmail.hmazud.submissionmovie2.provider.DatabaseContract.CONTENT_URI;

public class DetailMovie extends AppCompatActivity {

    public static final String MOVIE_ITEM = "movie_item";

    TextView textViewJudul;
    TextView textViewDeskripsi;
    TextView textViewRilis;
    TextView textViewRating;

    ImageView imageViewCover;
    ImageView imageViewPoster;
    ImageView imageViewFav;

    private FavoriteHelper favoriteHelper;
    private Boolean isFavorite = false;
    private MovieModel movieModel;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        movieModel = getIntent().getParcelableExtra("data");

        textViewJudul = findViewById(R.id.text_nama);
        textViewDeskripsi = findViewById(R.id.text_deskripsi);
        textViewRating = findViewById(R.id.text_rating);
        textViewRilis = findViewById(R.id.text_rilis);

        imageViewPoster = findViewById(R.id.img_poster);
        imageViewCover = findViewById(R.id.image_cover);
        imageViewFav = findViewById(R.id.img_fav);

        textViewJudul.setText(movieModel.getTitle());
        textViewDeskripsi.setText(movieModel.getOverview());
        textViewRating.setText("Rating : " + movieModel.getRating());
        textViewRilis.setText(movieModel.getRilis());

        Glide.with(this).load(BuildConfig.BASE_URL_IMG_185 + movieModel.getUrlImagePoster()).into(imageViewPoster);
        Glide.with(this).load(BuildConfig.BASE_URL_IMG_500 + movieModel.getUrlImageCover()).into(imageViewCover);

        loadDataSQLite();

        imageViewFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavorite) favoriteRemove();
                else favoriteSave();

                isFavorite = !isFavorite;
                favoriteSet();
            }
        });
    }

    private void favoriteSet() {
        if (isFavorite) imageViewFav.setImageResource(R.drawable.ic_favorite_black_24dp);
        else imageViewFav.setImageResource(R.drawable.ic_favorite_border_black_24dp);
    }

    private void favoriteSave() {
        //Log.d("TAG", "FavoriteSave: " + movieModel.getId());
        ContentValues cv = new ContentValues();

        cv.put(FavoriteColumns.COLUMN_ID, movieModel.getId());
        cv.put(FavoriteColumns.COLUMN_TITLE, movieModel.getTitle());
        cv.put(FavoriteColumns.COLUMN_BACKDROP, movieModel.getUrlImageCover());
        cv.put(FavoriteColumns.COLUMN_POSTER, movieModel.getUrlImagePoster());
        cv.put(FavoriteColumns.COLUMN_RELEASE_DATE, movieModel.getRilis());
        cv.put(FavoriteColumns.COLUMN_VOTE, movieModel.getRating());
        cv.put(FavoriteColumns.COLUMN_OVERVIEW, movieModel.getOverview());

        getContentResolver().insert(CONTENT_URI, cv);
        Toast.makeText(this, "Favorite", Toast.LENGTH_SHORT).show();
    }

    private void favoriteRemove() {
        getContentResolver().delete(
                Uri.parse(CONTENT_URI + "/" + movieModel.getId()),
                null,
                null
        );
        Toast.makeText(this, "Not Favorite", Toast.LENGTH_SHORT).show();
    }

    private void loadDataSQLite() {
        favoriteHelper = new FavoriteHelper(this);
        favoriteHelper.open();

        Cursor cursor = getContentResolver().query(
                Uri.parse(CONTENT_URI + "/" + movieModel.getId()),
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) isFavorite = true;
            cursor.close();
        }
        favoriteSet();
    }
}