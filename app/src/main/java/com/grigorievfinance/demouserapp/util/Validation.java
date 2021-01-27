package com.grigorievfinance.demouserapp.util;

import android.util.Patterns;

public class Validation {

    public static boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    public static boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}
