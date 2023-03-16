/*
 * Copyright (c) 2023. Walid Mograbi owner of YouTube channel الاقتصاد والاستثمار الشخصي .
 * All Rights Reserved.
 */

package com.tradingbotcourse.batuta.client;

import com.tradingbotcourse.batuta.api.binance.BinanceTradingSymbol;
import com.tradingbotcourse.batuta.intergration.binance.prices.WebSocketTickAdapter;
import com.tradingbotcourse.batuta.client.properties.WebSocketProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
@AllArgsConstructor
@Slf4j
public class BinanceWebSocketClient implements PricingClient<BinanceTradingSymbol> {

    private final WebSocketProperties webSocketProperties;

    @Override
    public Session connect(BinanceTradingSymbol symbol, WebSocketTickAdapter adapter) {
        try {
            URI uri = new URI(webSocketProperties.getUrl() + symbol.name().toLowerCase() + "@miniTicker");
            log.info("connect:+ Connecting to uri={}", uri);
            SslContextFactory sslContextFactory = new SslContextFactory.Client(true);
            WebSocketClient client = new WebSocketClient(new HttpClient(sslContextFactory));
            client.start();
            Session session = client.connect(adapter, uri).get();
            log.info("connect:- Connected to uri={}", uri);
            return session;
        } catch (URISyntaxException e) {
            throw new RuntimeException("URL Syntax error: " + e.getMessage());
        } catch (Throwable e) {
            throw new RuntimeException("WebSocket error: " + e.getMessage());
        }
    }
}
