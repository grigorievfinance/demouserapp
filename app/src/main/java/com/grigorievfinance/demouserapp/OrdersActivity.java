package com.grigorievfinance.demouserapp;

import androidx.annotation.RequiresApi;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.grigorievfinance.demouserapp.controller.OrderController;
import com.grigorievfinance.demouserapp.model.Order;
import com.grigorievfinance.demouserapp.model.User;

import org.json.JSONArray;

import java.util.List;

public class OrdersActivity extends ListActivity {

    private ArrayAdapter<Order> adapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_orders);


        Intent intent = getIntent();
        User user = (User)intent.getSerializableExtra("user");

        List<Order> orders = OrderController.getAll(user);
        if (orders != null) {
            listOfOrders(orders);
        } else {
            displayError();
        }
    }

    private void listOfOrders(List<Order> orders) {
        adapter = new ArrayAdapter<Order>(this, R.layout.support_simple_spinner_dropdown_item, orders);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(getApplicationContext(), "You choose " + (position + 1) + " order", Toast.LENGTH_SHORT).show();
    }

    private void displayError() {

    }
}