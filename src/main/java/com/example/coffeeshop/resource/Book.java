package com.example.coffeeshop.resource;

public class Book {
    private int id;
    private int cost;
    private String name;
    private String info;
    public Book(int id, int cost, String name, String info) {
        this.id = id;
        this.cost= cost;
        this.name= name;
        this.info=info;
    }
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
