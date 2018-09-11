package com.gmail.hmazud.submissionmovie2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.gmail.hmazud.submissionmovie2.provider.FavoriteColumns.COLUMN_BACKDROP;
import static com.gmail.hmazud.submissionmovie2.provider.FavoriteColumns.COLUMN_ID;
import static com.gmail.hmazud.submissionmovie2.provider.FavoriteColumns.COLUMN_OVERVIEW;
import static com.gmail.hmazud.submissionmovie2.provider.FavoriteColumns.COLUMN_POSTER;
import static com.gmail.hmazud.submissionmovie2.provider.FavoriteColumns.COLUMN_RELEASE_DATE;
import static com.gmail.hmazud.submissionmovie2.provider.FavoriteColumns.COLUMN_TITLE;
import static com.gmail.hmazud.submissionmovie2.provider.FavoriteColumns.COLUMN_VOTE;
import static com.gmail.hmazud.submissionmovie2.provider.FavoriteColumns.TABLE_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "db_movie";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_MOVIE = "create table " + TABLE_NAME + " (" +
                COLUMN_ID + " integer not null primary key autoincrement, " +
                COLUMN_TITLE + " text not null, " +
                COLUMN_POSTER + " text not null, " +
                COLUMN_BACKDROP + " text not null, " +
                COLUMN_RELEASE_DATE + " text not null, " +
                COLUMN_VOTE + " text not null, " +
                COLUMN_OVERVIEW + " text not null " +
                ");";

        sqLiteDatabase.execSQL(CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
