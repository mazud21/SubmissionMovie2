package com.gmail.hmazud.submissionmovie2.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieModel implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("release_date")
    @Expose
    private String rilis;

    @SerializedName("poster_path")
    @Expose
    private String urlImagePoster;

    @SerializedName("backdrop_path")
    @Expose
    private String urlImageCover;

    @SerializedName("vote_average")
    @Expose
    private float rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRilis() {
        return rilis;
    }

    public void setRilis(String rilis) {
        this.rilis = rilis;
    }

    public String getUrlImagePoster() {
        return urlImagePoster;
    }

    public void setUrlImagePoster(String urlImagePoster) {
        this.urlImagePoster = urlImagePoster;
    }

    public String getUrlImageCover() {
        return urlImageCover;
    }

    public void setUrlImageCover(String urlImageCover) {
        this.urlImageCover = urlImageCover;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.rilis);
        dest.writeString(this.urlImagePoster);
        dest.writeString(this.urlImageCover);
        dest.writeFloat(this.rating);
    }

    public MovieModel() {
    }

    protected MovieModel(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.rilis = in.readString();
        this.urlImagePoster = in.readString();
        this.urlImageCover = in.readString();
        this.rating = in.readFloat();
    }

    public static final Parcelable.Creator<MovieModel> CREATOR = new Parcelable.Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel source) {
            return new MovieModel(source);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };
}

