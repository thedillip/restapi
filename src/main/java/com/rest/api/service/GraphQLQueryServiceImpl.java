package com.rest.api.service;

import org.springframework.stereotype.Service;

@Service
public class GraphQLQueryServiceImpl implements GraphQLQueryService {

	@Override
	public String helloMessage(String name) {
		return "Hello, " + name + " ! Welcome to Graph QL API";
	}

}
