package com.grigorievfinance.demouserapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.grigorievfinance.demouserapp.controller.UserController;
import com.grigorievfinance.demouserapp.model.Balance;
import com.grigorievfinance.demouserapp.model.User;
import com.grigorievfinance.demouserapp.util.Validation;

import java.time.LocalDateTime;

import static com.grigorievfinance.demouserapp.util.Validation.DATE_TIME_FORMATTER;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        AndroidNetworking.initialize(getApplicationContext());

        final String username = usernameEditText.getText().toString();
        final String password = passwordEditText.getText().toString();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                final String username = usernameEditText.getText().toString();
                final String password = passwordEditText.getText().toString();

                if (!Validation.isUserNameValid(username)) {
                    usernameEditText.setError("Not valid username");
                } else if (!Validation.isPasswordValid(password)) {
                    passwordEditText.setError("Password must be >5 characters");
                } else  {
                    loadingProgressBar.setVisibility(View.VISIBLE);
                    login(username, password);
                }

            }
        });
    }

    private void login(String username, String password) {
        User user = UserController.login(username, password);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        loadingProgressBar.setVisibility(View.INVISIBLE);
        if (user.isAuth()) {
            Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
            openNewActivities(user);
        } else {
            Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
        }
    }

    private void openNewActivities(User user) {
        Intent intent = new Intent(this, BalanceActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}