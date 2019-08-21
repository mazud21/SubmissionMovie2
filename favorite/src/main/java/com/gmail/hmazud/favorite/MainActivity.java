package com.gmail.hmazud.favorite;

import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gmail.hmazud.favorite.adapter.FavoriteAdapter;

import static com.gmail.hmazud.favorite.provider.DatabaseContract.CONTENT_URI;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_favorite;

    private Cursor list;
    private FavoriteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_favorite = findViewById(R.id.rv_favorite);

        adapter = new FavoriteAdapter(list);
        rv_favorite.setLayoutManager(new LinearLayoutManager(this));
        rv_favorite.addItemDecoration(new DividerItemDecoration(rv_favorite.getContext(), DividerItemDecoration.VERTICAL));
        rv_favorite.setAdapter(adapter);

        new loadDataAsync().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new loadDataAsync().execute();
    }

    private class loadDataAsync extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return getContentResolver().query(
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
            adapter.replaceAll(list);
        }
    }
}
