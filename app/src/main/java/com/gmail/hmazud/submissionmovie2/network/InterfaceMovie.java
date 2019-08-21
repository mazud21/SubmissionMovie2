package com.gmail.hmazud.submissionmovie2.network;

import com.gmail.hmazud.submissionmovie2.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceMovie {
    @GET("search/movie")
    Call<Result> getSearchMovie(@Query("api_key") String api,
                             @Query("language") String lng,
                             @Query("query") String query);

    @GET("movie/now_playing")
    Call<Result> getNowPlayingMovie(@Query("api_key")String api_key,
                                    @Query("language")String language);

    @GET("movie/upcoming")
    Call<Result> getUpcomingMovie(@Query("api_key")String api_key,
                                  @Query("language")String language);
}