package com.grigorievfinance.demouserapp.util;

import android.os.Build;
import android.util.Patterns;

import androidx.annotation.RequiresApi;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Validation {

    private static final LocalDateTime MAX_DATE = LocalDateTime.now().plusYears(1);
    private static final LocalDateTime MIN_DATE = LocalDateTime.of(LocalDate.ofYearDay(2020, 01), LocalTime.MIN);
    private static final BigDecimal MAX_PRICE = BigDecimal.valueOf(999999);
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

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

    public static boolean isIdValid(int id) {
        return id > 100000 && id < 999999;
    }

    public static boolean isDateTimeValid(LocalDateTime dateTime) {
        return dateTime.isAfter(MIN_DATE) && dateTime.isBefore(MAX_DATE);
    }

    public static boolean isDescriptionValid(String description) {
        return !description.isEmpty() && description.length() < 255;
    }

    public static boolean isPriceValid(BigDecimal price) {
        return price.compareTo(BigDecimal.ZERO) > 0 && price.compareTo(MAX_PRICE) < 0;
    }

    public static boolean isDeadLineValid(LocalDate localDate) {
        return localDate.isAfter(MIN_DATE.toLocalDate()) && localDate.isBefore(MAX_DATE.toLocalDate());
    }
}
