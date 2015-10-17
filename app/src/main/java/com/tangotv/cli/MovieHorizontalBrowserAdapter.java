package com.tangotv.cli;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.netspace.tango.tangotv.R;
import com.tangotv.cli.models.TMovie;

import java.util.List;



public class MovieHorizontalBrowserAdapter extends RecyclerView.Adapter<MovieHorizontalBrowserAdapter.movieViewHolder> {

    //define inner class that handle calls to movie objects id's
    public static class movieViewHolder extends RecyclerView.ViewHolder {

        private CardView    cv;
        private TextView    movieTitle;
        private TextView    castName;
        private ImageView   moviePoster;


        //get id's from the xml movie card layout
        //use this method so id's wont be called every time recyler view is created(save resource big time)
        movieViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            movieTitle = (TextView) itemView.findViewById(R.id.movie_title);
            castName = (TextView) itemView.findViewById(R.id.movie_main_character);
            moviePoster = (ImageView) itemView.findViewById(R.id.movie_poster1);
        }

    }
    private final List<TMovie> movies;

    //call constructor to get movie from a movie list class
    //when the browse movie activity is created
    public MovieHorizontalBrowserAdapter(List<TMovie> movies){
        this.movies = movies;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public movieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //initialize a custom view here
        //specify the layout that each item of the RecyclerView should use
        //then pass the output to the constructor of the custom ViewHolder.
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_view_item, viewGroup, false);
        movieViewHolder movieHolder = new movieViewHolder(v);
        return movieHolder;
    }

    @Override
    public void onBindViewHolder(movieViewHolder holder, int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.castName.setText(movies.get(position).getPlot());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}


