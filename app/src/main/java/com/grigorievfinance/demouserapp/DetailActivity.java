package com.grigorievfinance.demouserapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.grigorievfinance.demouserapp.controller.OrderController;
import com.grigorievfinance.demouserapp.model.OrderTo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.grigorievfinance.demouserapp.util.Validation.*;

public class DetailActivity extends AppCompatActivity {

    private OrderTo orderTo;
    private EditText orderIdEd;
    private EditText orderDateEd;
    private EditText orderDescEd;
    private EditText orderPriceEd;
    private EditText orderDeadLineEd;

    private Button buttonSave;
    private Button buttonBack;
    private Button buttonDelete;
    private Button buttonCreate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        orderTo = (OrderTo)intent.getSerializableExtra("order");

        View view = findViewById(R.id.background);
        if (orderTo.isExcess()) {
            view.setBackgroundColor(Color.MAGENTA);
        } else {
            view.setBackgroundColor(Color.CYAN);
        }

        orderIdEd = findViewById(R.id.order_id);
        orderDateEd = findViewById(R.id.order_date);
        orderDescEd = findViewById(R.id.order_desc);
        orderPriceEd = findViewById(R.id.order_price);
        orderDeadLineEd = findViewById(R.id.order_dline);

        buttonSave = findViewById(R.id.button_save);
        buttonBack = findViewById(R.id.button_back);
        buttonDelete = findViewById(R.id.button_delete);
        buttonCreate = findViewById(R.id.button_create);

        String orderId = orderTo.getId().toString();
        String orderDate = orderTo.getDateTime().format(DATE_TIME_FORMATTER);
        String orderDesc = orderTo.getDescription();
        String orderPrice = orderTo.getPrice().toString();
        String orderDLine = orderTo.getDeadline().toString();

        orderIdEd.setText(orderId);
        orderDateEd.setText(orderDate);
        orderDescEd.setText(orderDesc);
        orderPriceEd.setText(orderPrice);
        orderDeadLineEd.setText(orderDLine);

        orderIdEd.setEnabled(false);
        orderDateEd.setEnabled(false);
        orderDescEd.setEnabled(false);
        orderPriceEd.setEnabled(false);
        orderDeadLineEd.setEnabled(false);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(orderId);
            }
        });

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create();
            }
        });
    }

    private void back() {
        Intent intent = new Intent(this, OrdersActivity.class);
        startActivity(intent);
    }

    private void change() {
        orderIdEd.setEnabled(true);
        orderDateEd.setEnabled(true);
        orderDescEd.setEnabled(true);
        orderPriceEd.setEnabled(true);
        orderDeadLineEd.setEnabled(true);
        buttonSave.setText("Save");

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                save(orderIdEd.getText().toString(), orderDateEd.getText().toString(), orderDescEd.getText().toString(), orderPriceEd.getText().toString(), orderDeadLineEd.getText().toString());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void save(String id, String date, String desc, String price, String dline) {
        int order_id = Integer.parseInt(id);
        LocalDateTime dateTime = LocalDateTime.parse(date, DATE_TIME_FORMATTER);
        BigDecimal order_price = BigDecimal.valueOf(Double.parseDouble(price));
        LocalDate deadLine = LocalDate.parse(dline);

        if (validation(order_id, dateTime, desc, order_price, deadLine)) {
            orderTo.setId(order_id);
            orderTo.setDateTime(dateTime);
            orderTo.setDescription(desc);
            orderTo.setPrice(order_price);
            orderTo.setDeadline(deadLine);
            if (OrderController.save(orderTo)) {
                Toast.makeText(getApplicationContext(), "Saved successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Not valid data", Toast.LENGTH_SHORT).show();
        }
    }

    private void delete(String id) {
        int order_id = Integer.parseInt(id);
        if (OrderController.delete(order_id)) {
            Toast.makeText(getApplicationContext(), "Deleted successful", Toast.LENGTH_SHORT).show();
            back();
        } else {
            Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_LONG).show();
        }
    }

    private void create() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean validation(int id, LocalDateTime dateTime, String description, BigDecimal price, LocalDate deadLine) {
        return isIdValid(id) && isDateTimeValid(dateTime) && isDescriptionValid(description) && isPriceValid(price) && isDeadLineValid(deadLine);
    }
}