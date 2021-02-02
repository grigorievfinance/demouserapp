package com.grigorievfinance.demouserapp.web;

import android.os.AsyncTask;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.androidnetworking.common.Priority;
import com.grigorievfinance.demouserapp.model.Order;
import com.grigorievfinance.demouserapp.util.Util;

import org.json.JSONException;

public class SaveOrder extends AsyncTask<Order, Void, ANResponse> {

    private final String url;
    private final String basicAuth;

    public SaveOrder(String url, String basicAuth) {
        this.url = url;
        this.basicAuth = basicAuth;
    }

    @Override
    protected ANResponse doInBackground(Order... orders) {
        Order order = orders[0];
        try {
            return makeRequest(url, basicAuth, order);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static synchronized ANResponse makeRequest(final String url, final String basicAuth, Order order) throws JSONException {
        ANRequest request = AndroidNetworking.put(url)
                .addHeaders("Authorization", basicAuth)
                .addHeaders("Content-Type", "application/json")
//                .addJSONObjectBody(Util.fromOrder(order))
                .addApplicationJsonBody(order)
                .setPriority(Priority.MEDIUM)
                .build();
        return request.executeForObject(Order.class);
    }
}
