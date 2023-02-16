package com.example.coffeeshop.resource;

import java.time.LocalDateTime;
import java.util.Map;

public class DoneOrder {
    private int orderID;
    private String status;
    private String time;
    private int sum;
    private String customer;

    public int getOrderID() {
        return orderID;
    }
    public DoneOrder(int orderID, String customer, String time, String status, int sum){
        this.setOrderID(orderID);
        this.setCustomer(customer);
        this.setTime(time);
        this.setStatus(status);
        this.setSum(sum);
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
