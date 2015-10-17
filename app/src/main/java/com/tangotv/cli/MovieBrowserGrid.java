package com.tangotv.cli;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.GridView;;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

import com.netspace.tango.tangotv.R;
import com.tangotv.cli.models.TMovie;
import com.tangotv.cli.services.MoviesService;

import java.util.List;

public class MovieBrowserGrid extends Activity implements MovieDetailsDialogFragment.ActivityCommunicator {
    /** Called when the activity is first created. */

    private MovieBrowserGridAdapter mAdapter;

    private GridView gridView;

    private static final String TAG = "MovieBrowserGrid";
    private static MoviesLoader moviesLoader;
    private TMovie selectedMovie;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_vertical_browser);
        moviesLoader = new MoviesLoader();
        moviesLoader.execute();
    }

    @Override
    public void sendData(String msg) {
        Intent intent = new Intent(getBaseContext(), MoviePlayer.class);
        System.out.println(msg);
        intent.putExtra("VIDEO_URL", msg);
        startActivity(intent);
    }

    private class MoviesLoader extends AsyncTask<Long, Void, List<TMovie>> {

        @Override
        protected List<TMovie> doInBackground(Long... params) {
            Long start = 0l;
            Long limit = 50l;

            if (params.length == 2) {
                start = params[0];
                limit = params[1];
            }

            return MoviesService.listMovies(start, limit);
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MovieBrowserGrid.this, "Inachakata", "Mchakato unaendelea. Tafadhali subiri...", true);
        }

        @Override
        protected void onPostExecute(final List<TMovie> movies) {

            progressDialog.dismiss();

            mAdapter = new MovieBrowserGridAdapter(MovieBrowserGrid.this, movies);
            // Set custom adapter to grid view
            gridView = (GridView) findViewById(R.id.gridView1);
            gridView.setAdapter(mAdapter);

            gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                private View mView;
                private Integer currentSelectedPosition = -1;

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if (currentSelectedPosition != position && mView != null) {
                        mView.setBackgroundColor(Color.rgb(59, 59, 59));
                    }
                    mView = view.findViewById(R.id.movie_bar_layout);
                    mView.setBackgroundColor(Color.rgb(0,175,255));
                    currentSelectedPosition = position;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            //gridView.setDrawSelectorOnTop(true);

            // Make GridView use your custom selector drawable
            //gridView.setSelector(getResources().getDrawable(R.drawable.movie_item_selector));

            // Implement On Item click listener
            gridView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    for (TMovie movie : movies) {
                        if (movie.getId() == id) {
                            selectedMovie = movie;
                            break;
                        }
                    }

                    // initiate fragment manager, initate custom dialog fragment
                    FragmentManager manager = getFragmentManager();
                    MovieDetailsDialogFragment movieDialog = new MovieDetailsDialogFragment();
                    movieDialog.setMovie(selectedMovie);
                    // show movie dialog
                    movieDialog.show(manager, "Movie Details");
                }
            });
        }
    }
}
