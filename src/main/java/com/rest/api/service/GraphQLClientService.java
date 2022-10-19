package com.rest.api.service;

import java.util.List;
import com.rest.api.response.CountryDetailsResponse;
import com.rest.api.response.StateDetailsResponse;

public interface GraphQLClientService {
	List<CountryDetailsResponse> getAllCountryDetails();

	StateDetailsResponse getAllStateDetailsByCountryCode(String countryCode);
}
