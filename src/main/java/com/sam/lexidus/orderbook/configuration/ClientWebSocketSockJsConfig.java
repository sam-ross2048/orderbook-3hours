package com.sam.lexidus.orderbook.configuration;

import com.sam.lexidus.orderbook.websocket.BinanceWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ClientWebSocketSockJsConfig {
    private static final String BINANCE_WEBSOCKET_URI = "wss://stream.binance.com:9443/ws/bnbbtc@depth";

    @Bean
    public WebSocketConnectionManager webSocketConnectionManager() {
        WebSocketConnectionManager manager = new WebSocketConnectionManager(
                webSocketClient(),
                webSocketHandler(),
                BINANCE_WEBSOCKET_URI
        );
        manager.setAutoStartup(true);
        return manager;
    }

    @Bean
    public SockJsClient webSocketClient() {
        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        transports.add(new RestTemplateXhrTransport());
        return new SockJsClient(transports);
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new BinanceWebSocketHandler();
    }
}
