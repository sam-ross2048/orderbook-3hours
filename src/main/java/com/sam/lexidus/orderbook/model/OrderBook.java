package com.sam.lexidus.orderbook.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderBook {
    private long lastUpdateId;
    private List<Order> bids = new ArrayList<>();
    private List<Order> asks = new ArrayList<>();
}
