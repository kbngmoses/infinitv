package com.tangotv.cli.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.tangotv.cli.models.TMovie;


import java.lang.reflect.Type;


public class MovieDeserializer implements JsonDeserializer<TMovie> {


    @Override
    public TMovie deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsObj = (JsonObject) json;
        TMovie.TMovieBuilder movieBuilder = new TMovie.TMovieBuilder()
                .setId(jsObj.get("id").getAsLong())
                .setTitle(jsObj.get("title").getAsString())
                .setPoster(jsObj.get("poster").getAsString())
                .setPlot(jsObj.get("plot").getAsString())
                .setVideoUrl(jsObj.get("video_url").getAsString());

        JsonArray jsArr = jsObj.getAsJsonArray("genre");
        String genre[] = new String[jsArr.size()];
        int i = 0;
        for (JsonElement el : jsObj.getAsJsonArray("genre")) {
            genre[i++] = el.getAsString();
        }
        movieBuilder.setGenre(genre);
        return movieBuilder.build();
    }
}

