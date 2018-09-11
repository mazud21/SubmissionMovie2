package com.gmail.hmazud.submissionmovie2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gmail.hmazud.submissionmovie2.BuildConfig;
import com.gmail.hmazud.submissionmovie2.DetailMovie;
import com.gmail.hmazud.submissionmovie2.Model.MovieModel;
import com.gmail.hmazud.submissionmovie2.Model.Result;
import com.gmail.hmazud.submissionmovie2.R;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<MovieModel> mData = new ArrayList<>();

    public MovieAdapter() {
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    public void replace(List<MovieModel> item) {
        mData.clear();
        mData = item;
        notifyDataSetChanged();
    }

    public void update(List<MovieModel> item) {
        mData.addAll(item);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Glide.with(holder.itemView.getContext())
                .load(BuildConfig.BASE_URL_IMG_185 + mData
                        .get(position)
                        .getUrlImagePoster())
                .into(holder.imageViewPoster);

        String overview;
        if (mData.get(position).getOverview().length() <= 30) {
            overview = mData.get(position).getOverview();
        } else {
            overview = mData.get(position).getOverview().substring(0, 60) + "...";
        }

        holder.textViewNama.setText(mData.get(position).getTitle());
        holder.textViewRilis.setText(mData.get(position).getRilis());
        holder.textViewDeskripsi.setText(overview);

        holder.cardView_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieModel movieModel = mData.get(position);
                Intent intent = new Intent(holder.itemView.getContext(), DetailMovie.class);
                intent.putExtra("data", movieModel);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
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

