package com.tangotv.cli.models;

import android.graphics.Bitmap;
import java.io.Serializable;

public class TMovie implements Comparable<TMovie>, Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String plot;
    private String video_url;
    private String poster;
    private Bitmap bitmap;
    private String [] genre;

    private TMovie() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getVideoUrl() {
        return video_url;
    }

    public void setVideoUrl(String video_url) {
        this.video_url = video_url;
    }

    public String getPoster() {
        return poster;
    }

    public void setPosterBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getPosterBitmap() {
        return bitmap;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String[] getGenre() { return genre; }

    public void setGenre(String[] genre) { this.genre = genre; }

    public String getGenreString() {
        if (genre == null) {
            return "null";
        } else if (genre.length < 1) {
            return genre[0];
        } else {
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < genre.length; i++) {
                if (i != genre.length - 2) {
                    strBuilder.append(", ");
                }
                strBuilder.append(genre[i]);
            }
            return strBuilder.toString();
        }
    }

    @Override
    public String toString() {
        return "TMovie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", plot='" + plot + '\'' +
                ", video_url='" + video_url + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }

    @Override
    public int compareTo(TMovie another) {
        return this.getId().compareTo(another.getId());
    }

    public static class TMovieBuilder {

        private TMovie movie;

        public TMovieBuilder() {
            movie = new TMovie();
        }

        public TMovieBuilder setId(Long id) {
            this.movie.setId(id);
            return this;
        }

        public TMovieBuilder setTitle(String title) {
            this.movie.setTitle(title);
            return this;
        }

        public TMovieBuilder setPlot(String plot) {
            this.movie.setPlot(plot);
            return this;
        }

        public TMovieBuilder setPoster(String poster) {
            this.movie.setPoster(poster);
            return this;
        }

        public TMovieBuilder setVideoUrl(String videoUrl) {
            this.movie.setVideoUrl(videoUrl);
            return this;
        }

        public TMovieBuilder setGenre(String[] genre) {
            this.movie.setGenre(genre);
            return this;
        }

        public TMovie build() {
            return movie;
        }
    }
}