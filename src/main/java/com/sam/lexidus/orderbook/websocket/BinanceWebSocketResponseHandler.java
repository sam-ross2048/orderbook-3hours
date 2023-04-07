package com.sam.lexidus.orderbook.websocket;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

import java.lang.reflect.Type;

public class BinanceWebSocketResponseHandler implements StompFrameHandler {

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return String.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        // TODO fix this
        String destination = headers.getDestination();
        String channel = destination.substring(destination.lastIndexOf("/") + 1);
        assert payload != null;
        System.out.println(payload.toString());
    }

    public void handleError(Throwable exception) {
        exception.printStackTrace();
        System.out.println(exception.getMessage());
    }
}
