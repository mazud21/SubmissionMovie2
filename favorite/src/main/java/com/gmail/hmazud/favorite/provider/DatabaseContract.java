package com.gmail.hmazud.favorite.provider;

import android.database.Cursor;
import android.net.Uri;

public class DatabaseContract {

    public static final String AUTHORITY = "com.gmail.hmazud.submissionmovie2";

    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(FavoriteColumns.TABLE_NAME)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static double getColumnDouble(Cursor cursor, String columnName) {
        return cursor.getDouble(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }
}
