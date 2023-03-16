/*
 * Copyright (c) 2023. Walid Mograbi owner of YouTube channel الاقتصاد والاستثمار الشخصي .
 * All Rights Reserved.
 */

package com.tradingbotcourse.batuta.domain.prices;

import com.tradingbotcourse.batuta.domain.entities.Price;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
@Slf4j
public class CurrentPrice implements PricePort {
    private Price currentPrice = null;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public void onPrice(Price price) {
        try {
            readWriteLock.writeLock().lock();
            log.info("onPrice:{}", price);

            currentPrice = price;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public Price current() {
        try {
            readWriteLock.readLock().lock();
            return currentPrice;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }


}
