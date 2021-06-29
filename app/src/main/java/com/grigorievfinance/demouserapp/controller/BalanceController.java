package com.grigorievfinance.demouserapp.controller;

import android.os.AsyncTask;
import android.util.Log;

import com.androidnetworking.common.ANResponse;
import com.grigorievfinance.demouserapp.model.Balance;
import com.grigorievfinance.demouserapp.model.User;
import com.grigorievfinance.demouserapp.util.Util;
import com.grigorievfinance.demouserapp.web.Read;

import org.json.JSONObject;

public class BalanceController {

    private static final String url = "https://soft-maker.com/binance/balance";

    public static Balance getBalance() {
        User user = UserController.getLogginUser();

        ANResponse response = null;

        AsyncTask<Void, Void, ANResponse> responseAsyncTask = new Read(url, user.getBasicAuth()).execute();
        try {
            response = responseAsyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = (JSONObject) response.getResult();

        if (response.isSuccess()) {
            try {
                return Util.balanceFromJson(jsonObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.d(BalanceController.class.toString(), "onError errorCode: " + response.getError().getErrorCode());
            Log.d(BalanceController.class.toString(), "onError errorBody: " + response.getError().getErrorBody());
            Log.d(BalanceController.class.toString(), "onError errorDetail: " + response.getError().getErrorDetail());
        }
        return null;
    }
}
