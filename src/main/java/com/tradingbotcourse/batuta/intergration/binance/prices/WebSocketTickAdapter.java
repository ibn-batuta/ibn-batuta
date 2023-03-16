/*
 * Copyright (c) 2023. Walid Mograbi owner of YouTube channel الاقتصاد والاستثمار الشخصي .
 * All Rights Reserved.
 */

package com.tradingbotcourse.batuta.intergration.binance.prices;

import com.tradingbotcourse.batuta.api.binance.mapper.BinancePriceToBotPriceMapper;
import com.tradingbotcourse.batuta.domain.prices.PricePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class WebSocketTickAdapter extends WebSocketAdapter {

    private final PricePort port;
    private final BinancePriceToBotPriceMapper binancePriceToBotPriceMapper;
    private final ApplicationEventPublisher publisher;


    public void onWebSocketClose(int statusCode, String reason) {
        super.onWebSocketClose(statusCode, reason);
        log.info("onWebSocketClose statusCode={} reason={}", statusCode, reason);
        publisher.publishEvent(new ReEstablishConnectionEvent(this));
    }

    public void onWebSocketConnect(Session session) {
        super.onWebSocketConnect(session);
        log.info("onWebSocketConnect");
    }

    public void onWebSocketError(Throwable cause) {
        super.onWebSocketError(cause);
        log.info("onWebSocketError", cause);
        publisher.publishEvent(new ReEstablishConnectionEvent(this));

    }

    public void onWebSocketText(String message) {
        super.onWebSocketText(message);
        try {
            port.onPrice(binancePriceToBotPriceMapper.apply(message));
        } catch (Exception e) {
            log.error("Error in WebSocket message={}", message, e);
        }
    }
}
