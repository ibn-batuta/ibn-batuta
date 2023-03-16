/*
 * Copyright (c) 2023. Walid Mograbi owner of YouTube channel الاقتصاد والاستثمار الشخصي .
 * All Rights Reserved.
 */

package com.tradingbotcourse.batuta.intergration.binance.prices;

import com.tradingbotcourse.batuta.api.binance.BinanceTradingSymbol;
import com.tradingbotcourse.batuta.client.PricingClient;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.websocket.api.Session;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BinancePriceSessionHolder {
    private final List<PricingClient> pricingClients;
    private List<Session> sessions;
    private final WebSocketTickAdapter webSocketTickAdapter;

    public BinancePriceSessionHolder(List<PricingClient> pricingClients, WebSocketTickAdapter webSocketTickAdapter) {
        this.pricingClients = pricingClients;
        this.webSocketTickAdapter = webSocketTickAdapter;
    }


    @PostConstruct
    private void connect() {
        sessions = pricingClients.stream()
                .map(client -> client.connect(BinanceTradingSymbol.BTCBUSD, webSocketTickAdapter))
                .collect(Collectors.toList());
    }

    @EventListener
    public void reEstablishSession(ReEstablishConnectionEvent event) {
        sessions.forEach(session -> {
            if (session.isOpen()) {
                session.close();
            }
        });
        connect();
    }
}
