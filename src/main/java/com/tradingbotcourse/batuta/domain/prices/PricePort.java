/*
 * Copyright (c) 2023. Walid Mograbi owner of YouTube channel الاقتصاد والاستثمار الشخصي .
 * All Rights Reserved.
 */

package com.tradingbotcourse.batuta.domain.prices;

import com.tradingbotcourse.batuta.domain.entities.Price;

public interface PricePort {
    void onPrice(Price price);

    Price current();
}
