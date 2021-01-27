package com.grigorievfinance.demouserapp.model;

import com.grigorievfinance.demouserapp.util.Util;

import java.io.Serializable;

public class User implements Serializable {

    private final String username;

    private final String basicAuth;

    private final boolean isAuth;

    public User(String username, String password, boolean isAuth) {
        this.username = username;
        this.isAuth = isAuth;
        this.basicAuth = Util.basicAuth(username, password);
    }

    public String getUsername() {
        return username;
    }

    public String getBasicAuth() {
        return basicAuth;
    }

    public boolean isAuth() {
        return isAuth;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", basicAuth='" + basicAuth + '\'' +
                ", isAuth=" + isAuth +
                '}';
    }
}
