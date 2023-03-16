/*
 * Copyright (c) 2023. Walid Mograbi owner of YouTube channel الاقتصاد والاستثمار الشخصي .
 * All Rights Reserved.
 */

package com.tradingbotcourse.batuta.intergration.binance.prices;

import org.springframework.context.ApplicationEvent;

public class ReEstablishConnectionEvent extends ApplicationEvent {
    public ReEstablishConnectionEvent(Object source) {
        super(source);
    }
}
