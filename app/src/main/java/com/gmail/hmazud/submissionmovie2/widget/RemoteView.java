package com.gmail.hmazud.submissionmovie2.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.gmail.hmazud.submissionmovie2.BuildConfig;
import com.gmail.hmazud.submissionmovie2.model.MovieModel;
import com.gmail.hmazud.submissionmovie2.R;

import java.util.concurrent.ExecutionException;

import static com.gmail.hmazud.submissionmovie2.provider.DatabaseContract.CONTENT_URI;

public class RemoteView implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private int widgetId;

    private Cursor cursor;

    public RemoteView(Context appContext, Intent intent) {
        context = appContext;
        widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

    }

    @Override
    public void onCreate() {
        cursor = context.getContentResolver().query(
                CONTENT_URI,
                null,
                null,
                null,
                null
                );
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        MovieModel movieModel = getItem(position);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.fav_widget_item);

        Bitmap bitmap = null;
        try {
            bitmap = Glide.with(context)
                    .asBitmap()
                    .load(BuildConfig.BASE_URL_IMG_500 + movieModel.getUrlImageCover())
                    .into(com.bumptech.glide.request.target.Target.SIZE_ORIGINAL, com.bumptech.glide.request.target.Target.SIZE_ORIGINAL)
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        remoteViews.setImageViewBitmap(R.id.img_fav_wid, bitmap);

        Bundle bundle = new Bundle();
        bundle.putInt(FavWidget.EXTRA_ITEM, position);
        Intent intent = new Intent();
        intent.putExtras(bundle);

        remoteViews.setOnClickFillInIntent(R.id.img_fav_wid, intent);

        return remoteViews;
    }

    private MovieModel getItem(int position) {
        if (!cursor.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid!");
        }
        return new MovieModel(cursor);
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
