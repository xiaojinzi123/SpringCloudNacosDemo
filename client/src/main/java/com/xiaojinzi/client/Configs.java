package com.xiaojinzi.client;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Configs {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplateConfig() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

}

