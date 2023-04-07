package com.sam.lexidus.orderbook.websocket;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

public class BinanceStompSessionHandler extends StompSessionHandlerAdapter {
    private final BinanceWebSocketResponseHandler responseHandler;

    public BinanceStompSessionHandler(BinanceWebSocketResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        responseHandler.handleError(exception);
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        responseHandler.handleError(exception);
    }
}
