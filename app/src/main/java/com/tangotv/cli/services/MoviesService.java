package com.tangotv.cli.services;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netspace.tango.tangotv.R;
import com.squareup.okhttp.OkHttpClient;
import com.tangotv.cli.configs.Config;
import com.tangotv.cli.models.TMovie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Query;

public class MoviesService {

    public static final String ENDPOINT_URL = "ENDPOINT_URL=";

    private static TMovie selectedMovie;

    private static long[] range = new long[2];

    private static final List<TMovie> movies = new ArrayList<>();

    private interface CoreMoviesService {
        @GET("/movies")
        List<TMovie> listMovies(
                @Header("Authorization") String authToken,
                @Query("start") Long start,
                @Query("limit") Long limit
        );
    }

    private static String authToken = "";

    private static final OkHttpClient okHttpClient = new OkHttpClient();

    private static OkHttpClient getOkHttpClient() {
        okHttpClient.setReadTimeout(1, TimeUnit.MINUTES);
        return okHttpClient;
    }

    private static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat("yyyy-MM-dd")
            .registerTypeAdapter(TMovie.class, new MovieDeserializer())
            .create();

    // TODO: Remove the hardcoded endpoint url
    private static final RestAdapter restAdapter = new RestAdapter.Builder()
            .setClient(new OkClient(getOkHttpClient()))
            .setEndpoint("https://tangotvbckend.herokuapp.com"/*Config.getAsString(ENDPOINT_URL)*/)
            .setConverter(new GsonConverter(gson))
            .build();

    private static final CoreMoviesService moviesService = restAdapter
            .create(CoreMoviesService.class);


    public static List<TMovie> listMovies(Long start, Long limit) {
        return moviesService.listMovies(authToken, start, limit);
    }

    public static void applyAuthToken(String authToken) {MoviesService.authToken = authToken;}

    public static void unapplyAuthToken() {
        authToken = null;
    }
}

