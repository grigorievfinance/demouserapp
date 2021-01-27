package com.grigorievfinance.demouserapp.controller;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.androidnetworking.common.ANResponse;
import com.grigorievfinance.demouserapp.model.Order;
import com.grigorievfinance.demouserapp.model.User;
import com.grigorievfinance.demouserapp.util.Util;
import com.grigorievfinance.demouserapp.web.Request;
import com.grigorievfinance.demouserapp.web.RequestAll;

import org.json.JSONArray;

import java.util.List;

import static com.grigorievfinance.demouserapp.util.Util.basicAuth;

public class OrderController {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<Order> getAll(User user) {

        String url = "https://soft-maker.com/rest/profile/orders";

        ANResponse response = null;
        AsyncTask<Void, Void, ANResponse> responseAsyncTask = new RequestAll(url, user.getBasicAuth()).execute();
        try {
            response = responseAsyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = (JSONArray) response.getResult();

        if (response.isSuccess()) {
            return Util.orderList(jsonArray);
        } else {
            return null;
        }
    }
}
