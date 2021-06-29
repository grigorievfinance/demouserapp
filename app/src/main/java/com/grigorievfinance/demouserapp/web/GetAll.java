package com.grigorievfinance.demouserapp.web;

import android.os.AsyncTask;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.androidnetworking.common.Priority;

public class GetAll extends AsyncTask<Void, Void, ANResponse> {
    private final String url;
    private final String basicAuth;

    public GetAll(String url, String basicAuth) {
        this.url = url;
        this.basicAuth = basicAuth;
    }

    @Override
    protected ANResponse doInBackground(Void... voids) {
        return getResponse(url, basicAuth);
    }

    public static synchronized ANResponse getResponse(final String url, final String basicAuth) {
        ANRequest request = AndroidNetworking.get(url)
                .addHeaders("Authorization", basicAuth)
                .addHeaders("Content-Type", "application/json")
                .setPriority(Priority.LOW)
                .build();

        return request.executeForJSONArray();
    }
}
