/*
 * Copyright (c) 2023. Walid Mograbi owner of YouTube channel الاقتصاد والاستثمار الشخصي .
 * All Rights Reserved.
 */

package com.tradingbotcourse.batuta.client;

import com.tradingbotcourse.batuta.intergration.binance.prices.WebSocketTickAdapter;
import org.eclipse.jetty.websocket.api.Session;

public interface PricingClient<T> {
    Session connect(T tradingSymbol, WebSocketTickAdapter adapter);
}
