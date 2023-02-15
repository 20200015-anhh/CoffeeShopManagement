package com.example.coffeeshop.resource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

public class Order {
    private int id;
    private Set<Drink> drinks;
    private Set<Book> books;
    private LinkedList<Integer> numDrink;
    private LinkedList<Integer> numBook;
    private String status;
    private LocalDateTime time;
    private double cost;
    private String customer;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(Set<Drink> drinks) {
        this.drinks = drinks;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public LinkedList<Integer> getNumDrink() {
        return numDrink;
    }

    public void setNumDrink(LinkedList<Integer> numDrink) {
        this.numDrink = numDrink;
    }

    public LinkedList<Integer> getNumBook() {
        return numBook;
    }

    public void setNumBook(LinkedList<Integer> numBook) {
        this.numBook = numBook;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
