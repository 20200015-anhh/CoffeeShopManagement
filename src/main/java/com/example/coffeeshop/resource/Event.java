package com.example.coffeeshop.resource;

public class Event {
    private int id;
    private int cost;
    private String name;
    private String status;
    public Event(int id, int cost, String name, String status) {
        this.id = id;
        this.cost= cost;
        this.name= name;
        this.status=status;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
