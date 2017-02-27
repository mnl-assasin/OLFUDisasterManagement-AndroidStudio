package com.olfu.olfudisasterapp.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by mykelneds on 25/02/2017.
 */

public interface ApiInterface {

    @POST("login")
    Call<Void> postLogin(@Body RequestLogin requestLogin);

    @GET("announcement")
    Call<Void> getAnnouncement();

}
