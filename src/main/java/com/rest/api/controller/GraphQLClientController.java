package com.rest.api.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.response.ApiEntity;
import com.rest.api.response.ApiResponseObject;
import com.rest.api.response.CountryDetailsResponse;
import com.rest.api.response.StateDetailsResponse;
import com.rest.api.service.GraphQLClientService;
import com.rest.api.util.ProjectConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
@Tag(name = "GraphQL API Client Controller")
public class GraphQLClientController {

	@Autowired
	private GraphQLClientService graphQLClientService;

	@Operation(summary = "getAllCountryDetails")
	@GetMapping(path = "/country")
	public ResponseEntity<ApiResponseObject> getAllCountryDetails() {
		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		List<CountryDetailsResponse> response = null;
		log.info("########## Hitting /country API in Controller Layer. ##########");
		try {
			response = graphQLClientService.getAllCountryDetails();
			if (CollectionUtils.isNotEmpty(response)) {
				message = ProjectConstant.DATA_FOUND;
				status = HttpStatus.OK;
			} else {
				message = ProjectConstant.DATA_NOT_FOUND;
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			log.info("########## Exception Occured in /country API in Controller Layer. ##########" + e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(new ApiEntity<>(message, response), httpHeaders, status);
	}

	@Operation(summary = "getAllCountryDetails")
	@GetMapping(path = "/{country_code}/state")
	public ResponseEntity<ApiResponseObject> getAllStateDetailsByCountryCode(
			@PathVariable(name = "country_code") String countryCode) {
		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		StateDetailsResponse response = null;
		log.info(
				"########## Hitting /{country_code}/state API in Controller Layer. :: Input Parameter :: countryCode = {} ##########",
				countryCode);
		try {
			response = graphQLClientService.getAllStateDetailsByCountryCode(countryCode);
			if (response != null) {
				message = ProjectConstant.DATA_FOUND;
				status = HttpStatus.OK;
			} else {
				message = ProjectConstant.DATA_NOT_FOUND;
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			log.info("########## Exception Occured in /{country_code}/state API in Controller Layer. ##########" + e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(new ApiEntity<>(message, response), httpHeaders, status);
	}
}
