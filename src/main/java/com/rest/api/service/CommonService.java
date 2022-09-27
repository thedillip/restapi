package com.rest.api.service;

import java.util.List;

import com.rest.api.dto.RandomQuoteDTO;
import com.rest.api.dto.UniversityDetailsDTO;
import com.rest.api.response.BankDetailsResponse;
import com.rest.api.response.PostOfficeDetailsResponse;

public interface CommonService {
	RandomQuoteDTO getRandomQuote();
	List<PostOfficeDetailsResponse> getPostOfficeDetailsByPinCode(String pinCode);
	List<PostOfficeDetailsResponse> getPostOfficeDetailsByBranchName(String branchName);
	BankDetailsResponse getBankDetailsByIfsc(String ifscCode);
	List<UniversityDetailsDTO> getUniversityDetailsByCountryName(String countryName);
}
