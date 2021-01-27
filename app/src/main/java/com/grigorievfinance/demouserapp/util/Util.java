package com.grigorievfinance.demouserapp.util;

import android.os.Build;
import android.util.Base64;

import androidx.annotation.RequiresApi;

import com.grigorievfinance.demouserapp.model.Order;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Util {

    public static String basicAuth(String username, String password) {
        String s = username + ":" + password;
        return "Basic " + Base64.encodeToString(s.getBytes(), Base64.NO_WRAP);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<Order> orderList(JSONArray jsonArray) {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i<jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                orders.add(toOrder(jsonObject));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orders;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static Order toOrder(JSONObject jsonObject) throws Exception {
        Integer id = jsonObject.getInt("id");
        LocalDateTime dateTime = LocalDateTime.parse(jsonObject.getString("dateTime"));
        String description = jsonObject.getString("description");
        BigDecimal price = BigDecimal.valueOf(jsonObject.getInt("price"));
        LocalDate deadLine = LocalDate.parse(jsonObject.getString("deadline"));
        boolean excess = jsonObject.optBoolean("excess");
        return new Order(id, dateTime, description, price, deadLine, excess);
    }
}
