package com.devmarvin.orderapi.common.config;

import com.devmarvin.orderapi.domain.converters.OrderConverter;
import com.devmarvin.orderapi.domain.converters.ProductConverter;
import com.devmarvin.orderapi.domain.converters.UserConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class ConverterConfig {
    @Value("${config.datetimeFormat}")
    private String dateTimeFormat;

    @Bean
    public ProductConverter getProductConverter(){
        return new ProductConverter();
    }

    @Bean
    public OrderConverter getOrderConverter(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormat);
        return new OrderConverter(formatter, getProductConverter());
    }

    @Bean
    public UserConverter getUserConverter(){
        return new UserConverter();
    }
}
