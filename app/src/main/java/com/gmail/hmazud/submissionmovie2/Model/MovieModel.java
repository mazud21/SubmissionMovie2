package com.gmail.hmazud.submissionmovie2.Model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static android.provider.BaseColumns._ID;
import static com.gmail.hmazud.submissionmovie2.provider.DatabaseContract.getColumnDouble;
import static com.gmail.hmazud.submissionmovie2.provider.DatabaseContract.getColumnInt;
import static com.gmail.hmazud.submissionmovie2.provider.DatabaseContract.getColumnString;
import static com.gmail.hmazud.submissionmovie2.provider.FavoriteColumns.COLUMN_BACKDROP;
import static com.gmail.hmazud.submissionmovie2.provider.FavoriteColumns.COLUMN_OVERVIEW;
import static com.gmail.hmazud.submissionmovie2.provider.FavoriteColumns.COLUMN_POSTER;
import static com.gmail.hmazud.submissionmovie2.provider.FavoriteColumns.COLUMN_RELEASE_DATE;
import static com.gmail.hmazud.submissionmovie2.provider.FavoriteColumns.COLUMN_TITLE;
import static com.gmail.hmazud.submissionmovie2.provider.FavoriteColumns.COLUMN_VOTE;

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
    private String rating;

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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
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
        dest.writeString(this.rating);
    }

    public MovieModel(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.title = getColumnString(cursor, COLUMN_TITLE);
        this.urlImagePoster = getColumnString(cursor, COLUMN_POSTER);
        this.urlImageCover = getColumnString(cursor, COLUMN_BACKDROP);
        this.rilis = getColumnString(cursor, COLUMN_RELEASE_DATE);
        this.rating = getColumnString(cursor, COLUMN_VOTE);
        this.overview = getColumnString(cursor, COLUMN_OVERVIEW);
    }

    protected MovieModel(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.rilis = in.readString();
        this.urlImagePoster = in.readString();
        this.urlImageCover = in.readString();
        this.rating = in.readString();
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

