package com.gmail.hmazud.favorite;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gmail.hmazud.favorite.model.FavoriteModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovie extends AppCompatActivity {

    @BindView(R.id.img_poster)
    ImageView iv_poster;

    @BindView(R.id.image_cover)
    ImageView iv_backdrop;

    @BindView(R.id.text_nama)
    TextView tv_title;

    @BindView(R.id.text_rilis)
    TextView tv_release_date;

    @BindView(R.id.text_rating)
    TextView tv_vote;

    @BindView(R.id.text_deskripsi)
    TextView tv_overview;

    private FavoriteModel item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);

        loadData();
        storeData();
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

    private void storeData() {
        if (item == null) return;

        Glide.with(this)
                .load(BuildConfig.BASE_URL_IMG_185 + item.getUrlImagePoster())
                .into(iv_poster);

        Glide.with(this)
                .load(BuildConfig.BASE_URL_IMG_500 + item.getUrlImageCover())
                .into(iv_backdrop);

        tv_title.setText(item.getTitle());
        tv_release_date.setText(item.getRilis());
        tv_vote.setText(item.getRating());
        tv_overview.setText(item.getOverview());
    }
}
