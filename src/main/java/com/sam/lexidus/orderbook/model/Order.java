package com.sam.lexidus.orderbook.model;

import lombok.Data;

@Data
public class Order {
    private Float size;
    private Float price;

    public Order(Float size, Float price) {
        this.size = size;
        this.price = price;
    }
}
