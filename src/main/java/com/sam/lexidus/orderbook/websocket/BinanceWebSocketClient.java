package com.sam.lexidus.orderbook.websocket;

import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class BinanceWebSocketClient {
    private static final String BINANCE_WEBSOCKET_URI = "wss://stream.binance.com:9443/ws/bnbbtc@depth";

    private final BinanceWebSocketResponseHandler responseHandler;

    public BinanceWebSocketClient() {
        this.responseHandler = new BinanceWebSocketResponseHandler();
    }

    public void connect() {
        List<Transport> transports = new ArrayList<>(2);

        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        transports.add(new RestTemplateXhrTransport());
        SockJsClient sockJsClient = new SockJsClient(transports);

        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new StringMessageConverter());

        StompSessionHandlerAdapter sessionHandler = new BinanceStompSessionHandler(responseHandler);

        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        byte[] headerBytes = Base64.getEncoder().encode(Instant.now().toString().getBytes());
        headers.set(WebSocketHttpHeaders.SEC_WEBSOCKET_KEY, headerBytes.toString());

        System.out.println(headers);

        stompClient.connectAsync(BINANCE_WEBSOCKET_URI, headers, sessionHandler);
    }
}
