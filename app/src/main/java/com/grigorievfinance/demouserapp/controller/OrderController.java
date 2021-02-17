package com.grigorievfinance.demouserapp.controller;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.androidnetworking.common.ANResponse;
import com.grigorievfinance.demouserapp.model.Order;
import com.grigorievfinance.demouserapp.model.OrderTo;
import com.grigorievfinance.demouserapp.model.User;
import com.grigorievfinance.demouserapp.util.Util;
import com.grigorievfinance.demouserapp.web.DeleteOrder;
import com.grigorievfinance.demouserapp.web.RequestAll;
import com.grigorievfinance.demouserapp.web.SaveOrder;

import org.json.JSONArray;

import java.util.List;

public class OrderController {

    //private static String url = "https://soft-maker.com/rest/profile/orders";
    private static String url = "http://192.168.0.27:8080/userwebapp/rest/profile/orders";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<OrderTo> getAll() {

        User user = UserController.getLogginUser();

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
            System.out.println(response.getError());
            return null;
        }
    }

    public static boolean save(OrderTo orderTo) {
        String restUrl = url + "/" + orderTo.getId();
        User user = UserController.getLogginUser();

        Order order = Util.toOrder(orderTo);

        ANResponse response = null;
        try {
            AsyncTask<Void, Void, ANResponse> responseAsyncTask = new SaveOrder(restUrl, user.getBasicAuth(), order).execute();
            response = responseAsyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (response.isSuccess()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean delete(int id) {
        String restUrl = url + "/" + id;
        User user = UserController.getLogginUser();

        ANResponse response = null;
        try {
            AsyncTask<Void, Void, ANResponse> responseAsyncTask = new DeleteOrder(restUrl, user.getBasicAuth()).execute();
            response = responseAsyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (response.isSuccess()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean create(OrderTo orderTo) {
        

        return false;
    }
}
