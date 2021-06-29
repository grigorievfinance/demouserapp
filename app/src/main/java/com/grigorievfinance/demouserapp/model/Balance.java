package com.grigorievfinance.demouserapp.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Balance implements Serializable {

    private BigDecimal balance;

    private int trades;

    private int positive;

    public Balance() {
    }

    public Balance(BigDecimal balance, int trades, int positive) {
        this.balance = balance;
        this.trades = trades;
        this.positive = positive;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getTrades() {
        return trades;
    }

    public void setTrades(int trades) {
        this.trades = trades;
    }

    public int getPositive() {
        return positive;
    }

    public void setPositive(int positive) {
        this.positive = positive;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "balance=" + balance +
                ", trades=" + trades +
                ", positive=" + positive +
                '}';
    }
}
