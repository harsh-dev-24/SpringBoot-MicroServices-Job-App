package com.harsh.jobms.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

	@LoadBalanced // Makes restTemplate to be used by load balanced client
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
