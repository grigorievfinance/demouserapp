package com.grigorievfinance.demouserapp.controller;

import android.os.AsyncTask;

import com.androidnetworking.common.ANResponse;
import com.grigorievfinance.demouserapp.model.User;
import com.grigorievfinance.demouserapp.web.Request;

import static com.grigorievfinance.demouserapp.util.Util.basicAuth;

public class UserController {

    private static User logginUser;

    public static User login(String username, String password) {
        String url = "https://soft-maker.com/rest/profile/";

        ANResponse response = null;
        AsyncTask<Void, Void, ANResponse> responseAsyncTask = new Request(url, basicAuth(username, password)).execute();
        try {
            response = responseAsyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (response.isSuccess()) {
            setUser(new User(username, password, true));
            return getLogginUser();
        } else {
            setUser(new User(username, password, false));
            return getLogginUser();
        }
    }

    private static void setUser(User user) {
        logginUser = user;
    }

    public static User getLogginUser() {
        return logginUser;
    }
}
