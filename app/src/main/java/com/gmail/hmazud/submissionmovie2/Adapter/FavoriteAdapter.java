package com.gmail.hmazud.submissionmovie2.Adapter;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gmail.hmazud.submissionmovie2.BuildConfig;
import com.gmail.hmazud.submissionmovie2.DetailMovie;
import com.gmail.hmazud.submissionmovie2.Model.MovieModel;
import com.gmail.hmazud.submissionmovie2.R;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private Cursor list;

    public FavoriteAdapter(Cursor items) {
        replace(items);
    }

    public void replace(Cursor items) {
        list = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.list_movie, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        return list.getCount();
    }

    private MovieModel getItem(int position) {
        if (!list.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid!");
        }
        return new MovieModel(list);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_poster)
        ImageView img_poster;

        @BindView(R.id.text_nama)
        TextView tv_title;

        @BindView(R.id.text_deskripsi)
        TextView tv_overview;

        @BindView(R.id.text_rilis)
        TextView tv_release_date;

        @BindView(R.id.layout)
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final MovieModel item) {
            tv_title.setText(item.getTitle());
            tv_overview.setText(item.getOverview());
            tv_release_date.setText(item.getRilis());
            Glide.with(itemView.getContext())
                    .load(BuildConfig.BASE_URL_IMG_185 + "w154" + item.getUrlImagePoster())
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.ic_image_black_24dp)
                            .centerCrop()
                    )
                    .into(img_poster);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), DetailMovie.class);
                    intent.putExtra(DetailMovie.MOVIE_ITEM, new Gson().toJson(item));
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
