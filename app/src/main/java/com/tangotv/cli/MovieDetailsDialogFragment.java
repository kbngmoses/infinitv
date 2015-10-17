package com.tangotv.cli;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.netspace.tango.tangotv.R;
import com.squareup.picasso.Picasso;
import com.tangotv.cli.models.TMovie;


public class MovieDetailsDialogFragment extends DialogFragment implements View.OnClickListener {

    private Button playMovieBtn;
    // Communicator communicator;
    private TextView movieDetailsView;
    private TextView detailHeader;
    private TextView detailSmallHeader;
    private ImageView detailsPoster;
    private TMovie movie;
    private ActivityCommunicator communicator;

    public interface ActivityCommunicator {
        void sendData(String msg);
    }

    public MovieDetailsDialogFragment() {}


    public MovieDetailsDialogFragment newInstance() {
        return new MovieDetailsDialogFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){
        // remove movie details dialog header section
        // then disallow removal of the dialog when sides of the dialog is clicked(not needed for tv but ooh well)
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // setCancelable(false);

        // inflate dialog xml find a watch movie button and set a click listener on it
        View view = inflater.inflate(R.layout.movie_details_dialog,container,false);

        movieDetailsView = (TextView) view.findViewById(R.id.movie_details_view);
        detailsPoster    = (ImageView) view.findViewById(R.id.detail_poster);
        detailHeader     = (TextView) view.findViewById(R.id.detail_header);
        detailSmallHeader= (TextView) view.findViewById(R.id.detail_small_header);

        playMovieBtn = (Button)view
                .findViewById(R.id.play_btn);
        playMovieBtn
                .setOnClickListener(this);
        movieDetailsView
                .setText(movie.getPlot().substring(0, 35));
        detailHeader
                .setText(movie.getTitle());
        detailSmallHeader
                .setText(movie.getGenreString());
        Picasso
                .with(getActivity()
                        .getApplicationContext())
                .load(movie.getPoster()).into(detailsPoster);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator = (ActivityCommunicator) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {

        }
    }

    @Override
    public void onStart()
    {
        super.onStart();

        // safety check
        if (getDialog() == null)
            return;

        // Set movie details dialog dimensions
        int dialogWidth = 1000;
        int dialogHeight = 400;

        getDialog().getWindow().setLayout(dialogWidth, dialogHeight);
    }

    @Override
    public void onClick(View view) {
        // check to see which button is clicked before taking acion
         if(view.getId() == R.id.play_btn){
             communicator.sendData(movie.getVideoUrl());
             dismiss();
         }
    }

    public void setMovie(TMovie movie) {
        this.movie = movie;
    }
}