/*
 * Copyright (c) 2023. Walid Mograbi owner of YouTube channel الاقتصاد والاستثمار الشخصي .
 * All Rights Reserved.
 */

package com.tradingbotcourse.batuta.api.binance.mapper;

import com.tradingbotcourse.batuta.domain.entities.Price;
import com.tradingbotcourse.batuta.domain.entities.TradingSymbol;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.function.Function;

@Component
public class BinancePriceToBotPriceMapper implements Function<String, Price> {
    @Override
    public Price apply(String price) {
        JsonObject object = (new Gson()).fromJson(price, JsonObject.class);

        return Price.builder()
                .symbol(TradingSymbol.valueOf(object.get("s").getAsString()))
                .open(object.get("o").getAsBigDecimal())
                .close(object.get("c").getAsBigDecimal())
                .high(object.get("h").getAsBigDecimal())
                .low(object.get("l").getAsBigDecimal())
                .volume(object.get("v").getAsBigDecimal())
                .quoteVolume(object.get("q").getAsBigDecimal())
                .build();
    }
}
