package com.grigorievfinance.demouserapp.web;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.androidnetworking.common.Priority;
import com.grigorievfinance.demouserapp.model.Order;
import com.grigorievfinance.demouserapp.util.Util;

import org.json.JSONException;

public class SaveNew extends AsyncTask<Void, Void, ANResponse> {

    private final String url;
    private final String basicAuth;
    private final Order order;

    public SaveNew(String url, String basicAuth, Order order) {
        this.url = url;
        this.basicAuth = basicAuth;
        this.order = order;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected ANResponse doInBackground(Void... voids) {
        try {
            return makeRequest(url, basicAuth, order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static synchronized ANResponse makeRequest(final String url, final String basicAuth, Order order) throws JSONException {
        ANRequest request = AndroidNetworking.post(url)
                .addHeaders("Authorization", basicAuth)
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(Util.fromOrder(order))
                .setPriority(Priority.MEDIUM)
                .build();
        return request.executeForJSONObject();
    }
}
