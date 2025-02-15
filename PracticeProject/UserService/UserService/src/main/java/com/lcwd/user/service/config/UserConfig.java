package com.lcwd.user.service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UserConfig {
	@Bean
	@LoadBalanced
	@Primary
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
