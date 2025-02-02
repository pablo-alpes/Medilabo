package com.medilabo.frontendservice.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Collections;

@Configuration
public class FeignConfig {

    @Bean
    public HttpMessageConverters httpMessageConverters() {
        HttpMessageConverter<?> converter = new MappingJackson2HttpMessageConverter();
        return new HttpMessageConverters(Collections.singletonList(converter));
    }
}