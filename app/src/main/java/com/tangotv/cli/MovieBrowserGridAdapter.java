package com.tangotv.cli;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netspace.tango.tangotv.R;
import com.squareup.picasso.Picasso;
import com.tangotv.cli.models.TMovie;

import org.w3c.dom.Text;

public class MovieBrowserGridAdapter extends BaseAdapter  {

    private final List<TMovie> moviesList;
    private final Activity     activity;

    public  MovieBrowserGridAdapter(Activity activity, List<TMovie> moviesList) {
        super();
        this.moviesList = moviesList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return moviesList.size();
    }

    @Override
    public TMovie getItem(int position) {
        return moviesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return moviesList.get(position).getId();
    }

    public static class ViewHolder
    {
        public ImageView imgViewFlag;
        public TextView  txtViewTitle;
        public TextView  txtViewCharacter;
        public LinearLayout linearView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder view;
        LayoutInflater inflater = activity.getLayoutInflater();

        if(convertView == null) {
            view = new ViewHolder();
            convertView = inflater.inflate(R.layout.movie_grid_item, parent, false);

            view.txtViewTitle = (TextView)  convertView.findViewById(R.id.movie_title);
            view.imgViewFlag  = (ImageView) convertView.findViewById(R.id.movie_poster1);
            view.txtViewCharacter = (TextView) convertView.findViewById(R.id.movie_main_character);

            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }

        TMovie movie = moviesList.get(position);

        view.txtViewTitle.setText(movie.getTitle());
        view.txtViewCharacter.setText(movie.getGenreString());

        Picasso
                .with(this.activity.getApplicationContext())
                .load(movie.getPoster())
                // .resize(240, 240)
                // .centerCrop()
                .into(view.imgViewFlag);

        return convertView;
    }
}