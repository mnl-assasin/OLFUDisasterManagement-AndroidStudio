package com.olfu.olfudisasterapp.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mykelneds on 25/02/2017.
 */

public class RequestLogin {

    @SerializedName("username")
    private String user;

    @SerializedName("password")
    private String password;

    public RequestLogin(String user, String password) {
        this.user = user;
        this.password = password;
    }
}
