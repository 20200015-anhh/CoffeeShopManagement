package com.example.coffeeshop.resource;

public class Book extends Product{
    private String info;
    public Book(int id, int cost, String name, String info) {
        this.setId(id);
        this.setCost(cost);
        this.setName(name);
        this.info=info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
