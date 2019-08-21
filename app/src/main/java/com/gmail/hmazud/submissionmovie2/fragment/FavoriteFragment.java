package com.gmail.hmazud.submissionmovie2.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.hmazud.submissionmovie2.adapter.FavoriteAdapter;
import com.gmail.hmazud.submissionmovie2.R;

import static com.gmail.hmazud.submissionmovie2.provider.DatabaseContract.CONTENT_URI;

public class FavoriteFragment extends Fragment {

    private Context context;

    RecyclerView rv_favorite;

    private Cursor list;
    private FavoriteAdapter favoriteAdapter;

    public FavoriteFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        context = view.getContext();

        rv_favorite = view.findViewById(R.id.rv_fav);

        favoriteAdapter = new FavoriteAdapter(list);
        rv_favorite.setLayoutManager(new LinearLayoutManager(context));
        rv_favorite.setAdapter(favoriteAdapter);

        new loadDataAsync().execute();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onResume() {
        super.onResume();
        new loadDataAsync().execute();
    }

    private class loadDataAsync extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return context.getContentResolver().query(
                    CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            );
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);

            list = cursor;
            favoriteAdapter.replace(list);
        }
    }
}
