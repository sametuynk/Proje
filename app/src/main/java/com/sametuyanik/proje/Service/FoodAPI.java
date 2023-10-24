package com.sametuyanik.proje.Service;

import com.sametuyanik.proje.Model.Receipts;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodAPI {

    @GET("/recipes/random")
    Call<Receipts> getProducts(@Query("number") String one, @Query("apiKey") String key);
}
