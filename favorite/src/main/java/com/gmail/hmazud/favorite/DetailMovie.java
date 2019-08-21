package com.gmail.hmazud.favorite;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gmail.hmazud.favorite.model.FavoriteModel;

public class DetailMovie extends AppCompatActivity {

    TextView tv_title;
    TextView tv_release_date;
    TextView tv_vote;
    TextView tv_overview;
    ImageView img_poster;
    ImageView img_cover;

    private FavoriteModel item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        tv_title = findViewById(R.id.text_nama);
        tv_overview = findViewById(R.id.text_deskripsi);
        tv_release_date = findViewById(R.id.text_rilis);
        tv_vote = findViewById(R.id.text_rating);

        img_poster = findViewById(R.id.img_poster);
        img_cover = findViewById(R.id.image_cover);

        loadData();

        tv_title.setText(item.getTitle());
        tv_overview.setText(item.getOverview());
        tv_release_date.setText(item.getRilis());
        tv_vote.setText(item.getRating());

        Glide.with(this)
                .load(BuildConfig.BASE_URL_IMG_185 + item.getUrlImagePoster())
                .into(img_poster);

        Glide.with(this)
                .load(BuildConfig.BASE_URL_IMG_500 + item.getUrlImageCover())
                .into(img_cover);
    }

    private void loadData() {
        Uri uri = getIntent().getData();
        if (uri == null) return;
        Cursor cursor = getContentResolver().query(
                uri,
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) item = new FavoriteModel(cursor);
            cursor.close();
        }
    }
}
