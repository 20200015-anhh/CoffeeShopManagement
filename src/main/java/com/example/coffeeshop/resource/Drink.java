package com.example.coffeeshop.resource;

public class Drink {
    private int id;
    private int cost;
    private String name;

    public Drink(int id, int cost, String name) {
        this.id = id;
        this.cost= cost;
        this.name= name;
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
