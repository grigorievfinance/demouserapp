package com.grigorievfinance.demouserapp.web;

import android.os.AsyncTask;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.androidnetworking.common.Priority;

public class Delete extends AsyncTask<Void, Void, ANResponse> {

    private final String url;
    private final String basicAuth;

    public Delete(String url, String basicAuth) {
        this.url = url;
        this.basicAuth = basicAuth;
    }

    protected ANResponse doInBackground(Void... voids) {
        try {
            return makeRequest(url,basicAuth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static synchronized ANResponse makeRequest(final String url, final String basicAuth) {
        ANRequest request = AndroidNetworking.delete(url)
                .addHeaders("Authorization", basicAuth)
                .setPriority(Priority.LOW)
                .build();
        return request.executeForOkHttpResponse();
    }
}
