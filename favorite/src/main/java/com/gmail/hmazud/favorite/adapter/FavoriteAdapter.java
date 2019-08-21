package com.gmail.hmazud.favorite.adapter;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gmail.hmazud.favorite.BuildConfig;
import com.gmail.hmazud.favorite.DetailMovie;
import com.gmail.hmazud.favorite.R;
import com.gmail.hmazud.favorite.model.FavoriteModel;

import static com.gmail.hmazud.favorite.provider.DatabaseContract.CONTENT_URI;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private Cursor list;

    public FavoriteAdapter(Cursor items) {
        replaceAll(items);
    }

    public void replaceAll(Cursor items) {
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

    private FavoriteModel getItem(int position) {
        if (!list.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid!");
        }
        return new FavoriteModel(list);
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        else return list.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title;
        private TextView tv_overview;
        private TextView tv_rilis;
        private ImageView img_poster;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.text_nama);
            tv_overview = itemView.findViewById(R.id.text_deskripsi);
            tv_rilis = itemView.findViewById(R.id.text_rilis);
            img_poster = itemView.findViewById(R.id.img_poster);
        }

        public void bind(final FavoriteModel item) {
            tv_title.setText(item.getTitle());
            tv_overview.setText(item.getOverview());
            tv_rilis.setText(item.getRilis());

            Glide.with(itemView.getContext())
                    .load(BuildConfig.BASE_URL_IMG_185 + item.getUrlImagePoster())
                    .into(img_poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), DetailMovie.class);
                    intent.setData(Uri.parse(CONTENT_URI + "/" + item.getId()));
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
