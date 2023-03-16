/*
 * Copyright (c) 2023. Walid Mograbi owner of YouTube channel الاقتصاد والاستثمار الشخصي .
 * All Rights Reserved.
 */

package com.tradingbotcourse.batuta.domain.entities;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public enum TradingSymbol {
    BTCBUSD(2, 5, "BTC");
    private int decimals;
    private int lotDecimals;
    private String crypto;

    TradingSymbol(int decimals, int lotDecimals, String crypto) {

        this.decimals = decimals;
        this.lotDecimals = lotDecimals;
        this.crypto = crypto;
    }

    public int decimals() {
        return decimals;
    }

    public int lotDecimals() {
        return lotDecimals;
    }

    public String crypto() {
        return crypto;
    }

    public static Optional<TradingSymbol> toSymbol(String symbol) {
        String upperSymbol = symbol.toUpperCase();
        try {
            return Optional.of(TradingSymbol.valueOf(upperSymbol));
        } catch (Exception e) {
            log.error("Failed to find symbol={}", symbol, e);
            return Optional.empty();
        }
    }
}
