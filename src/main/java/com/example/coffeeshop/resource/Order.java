package com.example.coffeeshop.resource;


import java.util.HashMap;
import java.util.Map;

import static com.example.coffeeshop.handler.DB.presentID;

public class Order extends DoneOrder{
    private Map<Product, Integer> order;
    public Order(){
        super(presentID,"","","Unfinished",0);
        this.setOrder(new HashMap<Product, Integer>());

    }
    public Map<Product, Integer> getOrder() {
        return order;
    }
    public void setOrder(Map<Product, Integer> order) {
        this.order = order;
    }
}
