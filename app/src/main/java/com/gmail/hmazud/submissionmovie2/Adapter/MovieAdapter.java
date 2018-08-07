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

import java.io.Serializable;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<MovieModel> mData;
    private Context context;

    public MovieAdapter(List<MovieModel> mData, Context context) {
        this.context = context;
        this.mData = mData;
    }

    //======start recyclerview ======

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context)
                .load(BuildConfig.BASE_URL_IMG_185 + mData
                        .get(position)
                        .getUrlImagePoster())
                .into(holder.imageViewCover);

        holder.textViewNama.setText(mData.get(position).getTitle());
        holder.textViewRilis.setText(mData.get(position).getRilis());
        holder.textViewDeskripsi.setText(mData.get(position).getOverview());

        holder.cardView_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieModel movieModel = mData.get(position);
                Intent intent = new Intent(context, DetailMovie.class);
                intent.putExtra("data", movieModel);
                context.startActivity(intent);
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
        private ImageView imageViewCover;
        private CardView cardView_layout;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNama = itemView.findViewById(R.id.text_nama);
            textViewDeskripsi = itemView.findViewById(R.id.text_deskripsi);
            textViewRilis = itemView.findViewById(R.id.text_rilis);
            imageViewCover = itemView.findViewById(R.id.imageView);
            cardView_layout = itemView.findViewById(R.id.layout);
        }
    }
}

