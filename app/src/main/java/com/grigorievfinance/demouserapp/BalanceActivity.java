package com.grigorievfinance.demouserapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.grigorievfinance.demouserapp.controller.BalanceController;
import com.grigorievfinance.demouserapp.model.Balance;
import com.grigorievfinance.demouserapp.model.User;

public class BalanceActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");

        Balance balance = BalanceController.getBalance();

        EditText edBalance = findViewById(R.id.balance);
        EditText edTrades = findViewById(R.id.trades);
        EditText edPositive = findViewById(R.id.positive);

        assert balance != null;
        edBalance.setText(balance.getBalance().toString());
        edTrades.setText(balance.getTrades() + "");
        edPositive.setText(balance.getPositive() + "");
    }
}