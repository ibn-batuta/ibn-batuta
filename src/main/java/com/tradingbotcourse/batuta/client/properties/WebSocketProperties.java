/*
 * Copyright (c) 2023. Walid Mograbi owner of YouTube channel الاقتصاد والاستثمار الشخصي .
 * All Rights Reserved.
 */

package com.tradingbotcourse.batuta.client.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "websocket")
@Data
public class WebSocketProperties {
    private String url;
}
