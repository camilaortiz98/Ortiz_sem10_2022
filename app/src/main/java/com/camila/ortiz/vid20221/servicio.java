package com.camila.ortiz.vid20221;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface servicio {

    @GET("N00029592")
    Call<List<pokemon>> LISTACall();

    @POST("N00029592/crear")
    Call<Void> crear(@Body pokemon pokemon);
}
