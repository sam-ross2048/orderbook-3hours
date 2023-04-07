package com.sam.lexidus.orderbook.service;

import com.sam.lexidus.orderbook.model.OrderBook;
import com.sam.lexidus.orderbook.rest.BinanceRestAPIHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderBookService {
    private final OrderBook orderBook = new OrderBook();
    private final Integer numLevels;

    private static final Logger logger = LoggerFactory.getLogger(OrderBookService.class);

    public OrderBookService(Integer numLevels) {
        this.numLevels = numLevels;
    }

    public void run() {
        BinanceRestAPIHandler.getSnapshot(orderBook);

        while (true) {
            printOrderBook();
            try {
                Thread.sleep(10000);
            } catch (Exception e) {
                logger.error("Exception thrown when sleeping", e);
            }
        }
    }

    private void printOrderBook() {
        System.out.printf("%-10s", "BID_SIZE");
        System.out.printf("%10s", "BID_PRICE");
        System.out.print("    ");
        System.out.printf("%-10s", "ASK_PRICE");
        System.out.printf("%10s%n", "ASK_SIZE");

        for (int i = 0; i < numLevels; i++) {
            System.out.printf("%-10.6f", orderBook.getBids().get(i).getSize());
            System.out.printf("%10.6f", orderBook.getBids().get(i).getPrice());
            System.out.print("    ");
            System.out.printf("%-10.6f", orderBook.getAsks().get(i).getPrice());
            System.out.printf("%10.6f%n", orderBook.getAsks().get(i).getSize());
        }
        System.out.print("\n\n");
    }
}
