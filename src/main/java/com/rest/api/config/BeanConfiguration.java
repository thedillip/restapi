package com.rest.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@Configuration
public class BeanConfiguration {
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public Gson getGson() {
		return new Gson();
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
