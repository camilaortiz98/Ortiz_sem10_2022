package com.camila.ortiz.vid20221;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface servicio {

    @GET("crearLibro")
    Call<List<libro>> LISTACall();

    @POST("crearLibro")
    Call<Void> crear(@Body libro libro);
}
