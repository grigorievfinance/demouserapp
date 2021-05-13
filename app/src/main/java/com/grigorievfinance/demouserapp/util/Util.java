package com.grigorievfinance.demouserapp.util;

import android.os.Build;
import android.util.Base64;

import androidx.annotation.RequiresApi;

import com.grigorievfinance.demouserapp.model.Order;
import com.grigorievfinance.demouserapp.model.OrderTo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.grigorievfinance.demouserapp.util.Validation.DATE_TIME_FORMATTER;

public class Util {

    public static String basicAuth(String username, String password) {
        String s = username + ":" + password;
        return "Basic " + Base64.encodeToString(s.getBytes(), Base64.NO_WRAP);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<OrderTo> orderList(JSONArray jsonArray) {
        List<OrderTo> orderTos = new ArrayList<>();
        for (int i = 0; i<jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                orderTos.add(fromJson(jsonObject));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderTos;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static OrderTo fromJson(JSONObject jsonObject) throws Exception {
        Integer id = jsonObject.getInt("id");
        LocalDateTime dateTime = LocalDateTime.parse(jsonObject.getString("dateTime"));
        String description = jsonObject.getString("description");
        BigDecimal price = BigDecimal.valueOf(jsonObject.getInt("price"));
        LocalDate deadLine = LocalDate.parse(jsonObject.getString("deadline"));
        boolean excess = jsonObject.optBoolean("excess");

        return new OrderTo(id, dateTime, description, price, deadLine, excess);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static JSONObject fromOrder(Order order) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", order.getId());
        jsonObject.put("dateTime", order.getDateTime());
        jsonObject.put("description", order.getDescription());
        jsonObject.put("price", order.getPrice());
        jsonObject.put("deadline", order.getDeadline().toString());

        return jsonObject;
    }

    public static Order toOrder(OrderTo orderTo) {
        return new Order(orderTo.getId(), orderTo.getDateTime(), orderTo.getDescription(), orderTo.getPrice(), orderTo.getDeadline());
    }
}
