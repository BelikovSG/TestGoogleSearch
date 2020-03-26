package com.example.testgooglesearch.model;

import com.example.testgooglesearch.model.response.ResponseBody;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchApi {
    @GET("customsearch/v1")
    Call<ResponseBody> getData(@Query("key") String key, @Query("cx") String sid, @Query("q") String query);
}
