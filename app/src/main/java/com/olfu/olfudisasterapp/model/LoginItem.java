package com.olfu.olfudisasterapp.model;

/**
 * Created by mleano on 2/28/2017.
 */

public class LoginItem {

    String username;
    String password;

    public LoginItem(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
