package com.grigorievfinance.demouserapp.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Order {

    private Integer id;

    private LocalDateTime dateTime;

    private String description;

    private BigDecimal price;

    private LocalDate deadline;

    private boolean excess;

    public Order() {
    }

    public Order(Integer id, LocalDateTime dateTime, String description, BigDecimal price, LocalDate deadline, boolean excess) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.price = price;
        this.deadline = deadline;
        this.excess = excess;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isExcess() {
        return excess;
    }

    public void setExcess(boolean excess) {
        this.excess = excess;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", deadline=" + deadline +
                ", excess=" + excess +
                '}';
    }
}
