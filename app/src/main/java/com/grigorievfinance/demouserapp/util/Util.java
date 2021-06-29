package com.grigorievfinance.demouserapp.util;

import android.util.Base64;

import com.grigorievfinance.demouserapp.model.Balance;

import org.json.JSONObject;

import java.math.BigDecimal;

public class Util {

    public static String basicAuth(String username, String password) {
        String s = username + ":" + password;
        return "Basic " + Base64.encodeToString(s.getBytes(), Base64.NO_WRAP);
    }

    public static Balance balanceFromJson(JSONObject jsonObject) throws Exception {
        BigDecimal balance = BigDecimal.valueOf(jsonObject.getInt("balance"));
        int trades = jsonObject.getInt("trades");
        int positive = jsonObject.getInt("positive");;
        return new Balance(balance, trades, positive);
    }
}
