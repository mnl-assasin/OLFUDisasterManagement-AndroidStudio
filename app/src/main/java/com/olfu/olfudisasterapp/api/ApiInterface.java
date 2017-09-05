package com.olfu.olfudisasterapp.api;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

/**
 * Created by mykelneds on 25/02/2017.
 */

public interface ApiInterface {

    @POST("login")
    Call<ResponseLogin> postLogin(@Body RequestLogin requestLogin);

    @Multipart
    @POST("register")
    Call<Void> postRegister(@PartMap()Map<String, RequestBody> partMap, @Part MultipartBody.Part file);

    @GET("announcement")
    Call<List<ResponseAnnouncement>> getAnnouncement();

    @Multipart
    @POST("report")
    Call<Void> postReport(@PartMap() Map<String, RequestBody> partMap, @Part MultipartBody.Part file);

    @Multipart
    @POST("user/{id}")
    Call<Void> postUserUpdate(@Path("id") String id, @PartMap() Map<String, RequestBody> partMap, @Part MultipartBody.Part file);






}
