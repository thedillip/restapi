package com.rest.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.dto.GuessGenderByNameDTO;
import com.rest.api.dto.RandomQuoteDTO;
import com.rest.api.dto.UniversityDetailsDTO;
import com.rest.api.response.ApiEntity;
import com.rest.api.response.ApiResponseObject;
import com.rest.api.response.BankDetailsResponse;
import com.rest.api.response.PostOfficeDetailsResponse;
import com.rest.api.service.CommonService;
import com.rest.api.util.ProjectConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@Tag(name = "Common Controller")
public class CommonController {

	@Autowired
	private CommonService commonService;

	@Operation(summary = "getRandomQuote")
	@GetMapping(path = "/quote")
	public ResponseEntity<ApiResponseObject> getRandomQuote() {

		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		RandomQuoteDTO response = null;
		log.info("############# Hitting getRandomQuote() in Controller Layer ###############");
		try {
			response = commonService.getRandomQuote();
			if (response != null) {
				message = "Quote Found";
				status = HttpStatus.OK;
			} else {
				message = "Quote Not Found";
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			log.info("############# Exception Occured in getRandomQuote() in Controller Layer ##########" + e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(new ApiEntity<RandomQuoteDTO>(message, response), httpHeaders, status);
	}

	@Operation(summary = "getPostOfficeDetailsByPinCode")
	@GetMapping(path = "/postoffice-details/{pin_code}")
	public ResponseEntity<ApiResponseObject> getPostOfficeDetailsByPinCode(
			@Parameter(description = "String", required = true) @PathVariable(name = "pin_code", required = true) String pinCode) {

		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		List<PostOfficeDetailsResponse> response = null;
		log.info("############# Hitting getPostOfficeDetailsByPinCode() in Controller Layer :: PinCode :: " + pinCode);
		try {
			response = commonService.getPostOfficeDetailsByPinCode(pinCode);
			if (!response.isEmpty() && response != null) {
				message = ProjectConstant.DATA_FOUND;
				status = HttpStatus.OK;
			} else {
				message = ProjectConstant.DATA_NOT_FOUND;
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			log.info(
					"############# Exception Occured in getPostOfficeDetailsByPinCode() in Controller Layer ########## "
							+ e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(new ApiEntity<List<PostOfficeDetailsResponse>>(message, response), httpHeaders,
				status);
	}

	@Operation(summary = "getPostOfficeDetailsByPostOfficeBranchName")
	@GetMapping(path = "/postoffice-details")
	public ResponseEntity<ApiResponseObject> getPostOfficeDetailsByBranchName(
			@Parameter(description = "String", required = true) @RequestParam(name = "branch_name", required = true) String branchName) {

		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		List<PostOfficeDetailsResponse> response = null;
		log.info(
				"############# Hitting getPostOfficeDetailsByBranchName() in Controller Layer :: PostOfficeBranchName :: "
						+ branchName);
		try {
			response = commonService.getPostOfficeDetailsByBranchName(branchName);
			if (!response.isEmpty() && response != null) {
				message = ProjectConstant.DATA_FOUND;
				status = HttpStatus.OK;
			} else {
				message = ProjectConstant.DATA_NOT_FOUND;
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			log.info(
					"############# Exception Occured in getPostOfficeDetailsByBranchName() in Controller Layer ########## "
							+ e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(new ApiEntity<List<PostOfficeDetailsResponse>>(message, response), httpHeaders,
				status);
	}

	@Operation(summary = "getBankDetailsByIfscCode")
	@GetMapping(path = "/bank-details/{ifsc_code}")
	public ResponseEntity<ApiResponseObject> getBankDetailsByIfsc(
			@Parameter(description = "String", required = true) @PathVariable(name = "ifsc_code", required = true) String ifscCode) {

		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		BankDetailsResponse response = null;
		log.info("############# Hitting getBankDetailsByIfsc() in Controller Layer :: IFSC Code :: " + ifscCode);
		try {
			response = commonService.getBankDetailsByIfsc(ifscCode);
			if (response != null) {
				message = ProjectConstant.DATA_FOUND;
				status = HttpStatus.OK;
			} else {
				message = ProjectConstant.DATA_NOT_FOUND;
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			log.info("############# Exception Occured in getBankDetailsByIfsc() in Controller Layer ##########" + e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(new ApiEntity<BankDetailsResponse>(message, response), httpHeaders, status);
	}

	@Operation(summary = "getAllUniversityDetailsByCountryName")
	@GetMapping(path = "/university-details")
	public ResponseEntity<ApiResponseObject> getUniversityDetailsByCountryName(
			@Parameter(description = "String", required = true) @RequestParam(name = "country_name", required = true) String countryName) {
		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		List<UniversityDetailsDTO> response = null;
		log.info("############# Hitting getUniversityDetails() in Controller Layer :: countryName :: " + countryName);
		try {
			response = commonService.getUniversityDetailsByCountryName(countryName);
			if (!response.isEmpty()) {
				status = HttpStatus.OK;
				message = ProjectConstant.DATA_FOUND;
			} else {
				status = HttpStatus.NOT_FOUND;
				message = ProjectConstant.DATA_NOT_FOUND;
			}
		} catch (Exception e) {
			log.info("########## Exception Occured in getUniversityDetails() in Controller Layer ##########", e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(new ApiEntity<>(message, response), httpHeaders, status);

	}

	@Operation(summary = "Guess Gender By Name")
	@GetMapping(path = "/guess-gender")
	public ResponseEntity<ApiResponseObject> guessGenderByName(
			@Parameter(description = "String", required = true) @RequestParam(name = "name", required = true) String name) {

		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		GuessGenderByNameDTO response = null;
		log.info("############# Hitting /guess-gender API in Controller Layer ###############");
		try {
			response = commonService.guessGenderByName(name);
			if (response != null) {
				message = ProjectConstant.DATA_FOUND;
				status = HttpStatus.OK;
			} else {
				message = ProjectConstant.DATA_NOT_FOUND;
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			log.info("############# Exception Occured in /guess-gender in Controller Layer ##########" + e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(new ApiEntity<>(message, response), httpHeaders, status);
	}
}
