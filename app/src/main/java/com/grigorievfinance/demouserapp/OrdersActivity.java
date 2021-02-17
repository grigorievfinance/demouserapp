package com.grigorievfinance.demouserapp;

import androidx.annotation.RequiresApi;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.grigorievfinance.demouserapp.controller.OrderController;
import com.grigorievfinance.demouserapp.model.OrderTo;
import com.grigorievfinance.demouserapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class OrdersActivity extends ListActivity {

    private ArrayAdapter<String> descriptionAdapter;

    private List<OrderTo> orderTos = null;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        User user = (User)intent.getSerializableExtra("user");

        orderTos = OrderController.getAll();

        if (orderTos != null) {
            listOfDescriptions(getDescriptions(orderTos));
        } else {
            displayError();
        }
    }

    private void listOfDescriptions(List<String> descriptions) {
        descriptionAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, descriptions);
        setListAdapter(descriptionAdapter);
    }

    private List<String> getDescriptions(List<OrderTo> orderTos) {
        List<String> strings = new ArrayList<>();
        for (OrderTo orderTo : orderTos) {
            strings.add(orderTo.getDescription());
        }
        return strings;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        OrderTo orderTo = orderTos.get(position);
        Toast.makeText(getApplicationContext(), "You choose " + orderTo.getDescription(), Toast.LENGTH_SHORT).show();
        openDetail(orderTo);
    }

    private void displayError() {
        Toast.makeText(getApplicationContext(), "List of orders is null", Toast.LENGTH_LONG).show();
    }

    private void openDetail(OrderTo orderTo) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("order", orderTo);
        startActivity(intent);
    }
}