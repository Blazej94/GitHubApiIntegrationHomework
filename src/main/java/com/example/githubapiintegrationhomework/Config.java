package com.example.githubapiintegrationhomework;

import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
  class Config {

    @Bean
    Decoder feignDecoder() {
        return new JacksonDecoder();
    }
}