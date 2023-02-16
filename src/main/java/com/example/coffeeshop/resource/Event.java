package com.example.coffeeshop.resource;

public class Event extends Product {
    String date;
    String status;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Event(int id, int cost, String name, String date) {
        this.setId(id);
        this.setCost(cost);
        this.setName(name);
        this.date = date;
        this.status = "Unfinished";
    }
}
