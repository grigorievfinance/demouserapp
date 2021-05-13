package com.grigorievfinance.demouserapp.controller;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.androidnetworking.common.ANResponse;
import com.grigorievfinance.demouserapp.model.Order;
import com.grigorievfinance.demouserapp.model.OrderTo;
import com.grigorievfinance.demouserapp.model.User;
import com.grigorievfinance.demouserapp.util.Util;
import com.grigorievfinance.demouserapp.web.DeleteOrder;
import com.grigorievfinance.demouserapp.web.RequestAll;
import com.grigorievfinance.demouserapp.web.SaveNew;
import com.grigorievfinance.demouserapp.web.SaveOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class OrderController {

    private static String url = "https://soft-maker.com/rest/profile/orders";

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
            Log.d(OrderController.class.toString(), "onError errorCode: " + response.getError().getErrorCode());
            Log.d(OrderController.class.toString(), "onError errorBody: " + response.getError().getErrorBody());
            Log.d(OrderController.class.toString(), "onError errorDetail: " + response.getError().getErrorDetail());
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

        if (response.isSuccess() || response.getError().getErrorCode() == 0) {
            return true;
        } else {
            Log.d(OrderController.class.toString(), "onError errorCode: " + response.getError().getErrorCode());
            Log.d(OrderController.class.toString(), "onError errorBody: " + response.getError().getErrorBody());
            Log.d(OrderController.class.toString(), "onError errorDetail: " + response.getError().getErrorDetail());
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
            Log.d(OrderController.class.toString(), "onError errorCode: " + response.getError().getErrorCode());
            Log.d(OrderController.class.toString(), "onError errorBody: " + response.getError().getErrorBody());
            Log.d(OrderController.class.toString(), "onError errorDetail: " + response.getError().getErrorDetail());
            return false;
        }
    }

    public static boolean saveNew(Order order) {
        User user = UserController.getLogginUser();

        ANResponse response = null;
        try {
            AsyncTask<Void, Void, ANResponse> responseAsyncTask = new SaveNew(url, user.getBasicAuth(), order).execute();
            response = responseAsyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (response.isSuccess() || response.getError().getErrorCode() == 0) {
            return true;
        } else {
            Log.d(OrderController.class.toString(), "onError errorCode: " + response.getError().getErrorCode());
            Log.d(OrderController.class.toString(), "onError errorBody: " + response.getError().getErrorBody());
            Log.d(OrderController.class.toString(), "onError errorDetail: " + response.getError().getErrorDetail());
            System.out.println(response.getError().getErrorCode());
            System.out.println(response.getError().getErrorBody());
            System.out.println(response.getError().getErrorDetail());
            return false;
        }
    }
}
