package com.rest.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.rest.api.response.CountryDetailsResponse;
import com.rest.api.response.StateDetailsResponse;

import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GraphQLClientServiceImpl implements GraphQLClientService {

	@Autowired
	private GraphQLWebClient graphQLWebClient;
	@Value("${graphql.client.url}")
	private String graphQLClientUrl;

	@Override
	public List<CountryDetailsResponse> getAllCountryDetails() {
		log.info("########## Entered into getAllCountryDetails() in Service Layer. ##########");
		final String queryStr = "query{\r\n" + "  countries{\r\n" + "    name\r\n" + "    code\r\n" + "    capital\r\n"
				+ "    currency\r\n" + "    phone\r\n" + "  }\r\n" + "}";

		GraphQLRequest graphQLRequest = GraphQLRequest.builder().query(queryStr).build();

		log.info("########## Hitting GraphQL API ########## :: API URL = {} , GraphQL Query Body = {} ##########",
				graphQLClientUrl, queryStr);

		GraphQLResponse graphQLResponse = graphQLWebClient.post(graphQLRequest).block();

		List<CountryDetailsResponse> countryDetailsResponseList = graphQLResponse.get("countries", ArrayList.class);

		return countryDetailsResponseList;
	}

	@Override
	public StateDetailsResponse getAllStateDetailsByCountryCode(String countryCode) {
		Map<String, Object> variables = new HashMap<>();
		variables.put("code", countryCode);
		log.info("########## Entered into getAllStateDetailsByCountryCode() in Service Layer. ##########");
		final String queryStr = "query country($code:ID!){\r\n"
				+ "  country(code:$code){\r\n"
				+ "    code\r\n"
				+ "    name\r\n"
				+ "    capital\r\n"
				+ "    states{\r\n"
				+ "      code\r\n"
				+ "      name\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "}";
		GraphQLRequest graphQLRequest = GraphQLRequest.builder().query(queryStr).variables(variables).build();

		log.info("########## Hitting GraphQL API ########## :: API URL = {} , GraphQL Query Body = {} ##########",
				graphQLClientUrl, queryStr);

		GraphQLResponse graphQLResponse = graphQLWebClient.post(graphQLRequest).block();

		StateDetailsResponse stateDetailsResponse = graphQLResponse.get("country", StateDetailsResponse.class);

		return stateDetailsResponse;
	}

}
