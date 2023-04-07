package com.sam.lexidus.orderbook;

import com.sam.lexidus.orderbook.service.OrderBookService;
import com.sam.lexidus.orderbook.websocket.BinanceWebSocketClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderbookApplication {
	private static BinanceWebSocketClient client = new BinanceWebSocketClient();

	public static void main(String[] args) {
		// TODO: Take in argument to set number of levels in order book.
		SpringApplication.run(OrderbookApplication.class, args);

		OrderBookService orderBookService = new OrderBookService(10);

		orderBookService.run();
	}

}
