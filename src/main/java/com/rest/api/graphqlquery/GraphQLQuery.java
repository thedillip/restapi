package com.rest.api.graphqlquery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.rest.api.service.GraphQLQueryService;

@Controller
public class GraphQLQuery {

	@Autowired
	private GraphQLQueryService graphQLQueryService;

	@QueryMapping(value = "message")
	public String helloMessage(@Argument(name = "name") String name) {
		return graphQLQueryService.helloMessage(name);
	}
}
