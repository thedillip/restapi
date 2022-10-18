package com.rest.api.graphqlquery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.rest.api.response.BankDetailsResponse;
import com.rest.api.service.CommonService;
import com.rest.api.service.GraphQLQueryService;

@Controller
public class GraphQLQuery {

	@Autowired
	private GraphQLQueryService graphQLQueryService;
	@Autowired
	private CommonService commonService;

	@QueryMapping(value = "message")
	public String helloMessage(@Argument(name = "name") String name) {
		return graphQLQueryService.helloMessage(name);
	}

	@QueryMapping(value = "bankDetails")
	public BankDetailsResponse getBankDetailsByIfsc(@Argument(name = "ifsc") String ifsc) {
		return commonService.getBankDetailsByIfsc(ifsc);
	}
}
