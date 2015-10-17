package com.tangotv.cli;

import android.app.Activity;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.netspace.tango.tangotv.R;
import com.tangotv.cli.models.TMovie;

import java.util.List;


public class MovieBrowser extends Activity {

    private RecyclerView rv;
    private List<TMovie> movies;
    private GridLayoutManager lLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_browser);

        initializeData();
        initializeAdapter();
    }

    //This method creates an ArrayList that has three movie objects
    private void initializeData() {

    }

    private void initializeAdapter() {
        rv = (RecyclerView) findViewById(R.id.movie_recycler_view);
        rv.setHasFixedSize(true);

        lLayout = new GridLayoutManager(this, 4);
        // use a linear layout manager
        //LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(lLayout);

        // setting an adapter
        MovieHorizontalBrowserAdapter movieAdapter = new MovieHorizontalBrowserAdapter(movies);
        rv.setAdapter(movieAdapter);
    }
}
