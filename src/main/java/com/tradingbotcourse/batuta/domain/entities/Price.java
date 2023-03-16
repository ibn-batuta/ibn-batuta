/*
 * Copyright (c) 2023. Walid Mograbi owner of YouTube channel الاقتصاد والاستثمار الشخصي .
 * All Rights Reserved.
 */

package com.tradingbotcourse.batuta.domain.entities;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Price {
    public final TradingSymbol symbol;
    public final BigDecimal open;
    public final BigDecimal close;
    public final BigDecimal high;
    public final BigDecimal low;
    public final BigDecimal volume;
    public final BigDecimal quoteVolume;
    public final boolean isClosed;
}
