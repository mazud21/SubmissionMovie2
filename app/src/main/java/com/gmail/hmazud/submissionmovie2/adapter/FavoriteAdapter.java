package com.gmail.hmazud.submissionmovie2.adapter;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
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
import com.gmail.hmazud.submissionmovie2.model.MovieModel;
import com.gmail.hmazud.submissionmovie2.R;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private Cursor list;

    public FavoriteAdapter(Cursor items) {
        replace(items);
    }

    public void replace(Cursor items) {
        list = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.list_movie, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.textViewNama.setText(getItem(position).getTitle());
        holder.textViewDeskripsi.setText(getItem(position).getOverview());
        holder.textViewRilis.setText(getItem(position).getRilis());

        Glide.with(holder.itemView.getContext())
                .load(BuildConfig.BASE_URL_IMG_185 + getItem(position).getUrlImagePoster())
                .apply(new RequestOptions())
                .into(holder.imageViewPoster);

        holder.cardView_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MovieModel movieModel = getItem(position);
                Intent intent = new Intent(holder.itemView.getContext(), DetailMovie.class);
                intent.putExtra("data", movieModel);
                holder.itemView.getContext().startActivity(intent);
            }
        });
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
        private TextView textViewNama;
        private TextView textViewDeskripsi;
        private TextView textViewRilis;
        private ImageView imageViewPoster;
        private CardView cardView_layout;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNama = itemView.findViewById(R.id.text_nama);
            textViewDeskripsi = itemView.findViewById(R.id.text_deskripsi);
            textViewRilis = itemView.findViewById(R.id.text_rilis);
            imageViewPoster = itemView.findViewById(R.id.img_poster);
            cardView_layout = itemView.findViewById(R.id.layout);
        }
    }
}

