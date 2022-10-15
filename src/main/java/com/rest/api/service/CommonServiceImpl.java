package com.rest.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.rest.api.dto.BankDetailsDTO;
import com.rest.api.dto.GuessGenderByNameDTO;
import com.rest.api.dto.PinCodeDTO;
import com.rest.api.dto.PinCodeDetailsDTO;
import com.rest.api.dto.RandomQuoteDTO;
import com.rest.api.dto.UniversityDetailsDTO;
import com.rest.api.response.BankDetailsResponse;
import com.rest.api.response.ConsumeUniversityBody;
import com.rest.api.response.PostOfficeDetailsResponse;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommonServiceImpl implements CommonService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Gson gson;

	@Value("${randomquote.url}")
	private String randomQuoteUrl;
	@Value("${pincode.url}")
	private String pinCodeUrl;
	@Value("${bankdetails.url}")
	private String bankDetailsUrl;
	@Value("${universitydetails.url}")
	private String universityDetailsUrl;
	@Value("${guessgender.url}")
	private String guessGenderUrl;

	@Override
	public RandomQuoteDTO getRandomQuote() {
		RandomQuoteDTO response = null;
		String apiUrl = randomQuoteUrl;
		String fetchDataFromOtherApi = fetchDataFromOtherApi(apiUrl);
		try {
			log.info("############# Hitting getRandomQuote() ServiceImpl Layer :: apiUrl :: " + apiUrl);
			if (fetchDataFromOtherApi != null) {
				response = gson.fromJson(fetchDataFromOtherApi, RandomQuoteDTO.class);
			} else {
				response = new RandomQuoteDTO();
				response.setAuthor("Dillip K Sahoo");
				response.setContent("The greatest battle are fought with the closest people.");
			}
		} catch (Exception e) {
			log.info("############# Exception Occured in getRandomQuote() in ServiceImpl Layer ##########" + e);
		}
		return response;
	}

	@Override
	public List<PostOfficeDetailsResponse> getPostOfficeDetailsByPinCode(String pinCode) {
		String apiUrl = pinCodeUrl + "pincode/" + pinCode;
		log.info("############# Hitting getPostOfficeDetailsByPinCode() ServiceImpl Layer :: apiUrl :: " + apiUrl);
		List<PostOfficeDetailsResponse> reList = getPostOfficeDetailsByPinCodeOrBranchName(apiUrl);
		return reList;
	}

	@Override
	public List<PostOfficeDetailsResponse> getPostOfficeDetailsByBranchName(String branchName) {
		String apiUrl = pinCodeUrl + "postoffice/" + branchName;
		log.info("############# Hitting getPostOfficeDetailsByBranchName() ServiceImpl Layer :: apiUrl :: " + apiUrl);
		List<PostOfficeDetailsResponse> reList = getPostOfficeDetailsByPinCodeOrBranchName(apiUrl);
		return reList;
	}

	@Override
	public BankDetailsResponse getBankDetailsByIfsc(String ifscCode) {
		BankDetailsResponse response = null;
		String apiUrl = bankDetailsUrl + ifscCode;
		log.info("############# Hitting getBankDetailsByIfsc() ServiceImpl Layer :: apiUrl :: " + apiUrl);
		String fetchDataFromOtherApi = fetchDataFromOtherApi(apiUrl);
		try {
			if (fetchDataFromOtherApi != null) {
				BankDetailsDTO fetchBankDetailsByIfsc = gson.fromJson(fetchDataFromOtherApi, BankDetailsDTO.class);
				if (fetchBankDetailsByIfsc != null) {
					response = new BankDetailsResponse();
					response.setMicrCode(fetchBankDetailsByIfsc.getMICR());
					response.setBranchName(fetchBankDetailsByIfsc.getBRANCH());
					response.setAddress(fetchBankDetailsByIfsc.getADDRESS());
					response.setState(fetchBankDetailsByIfsc.getSTATE());
					response.setContact(fetchBankDetailsByIfsc.getCONTACT());
					response.setCityName(fetchBankDetailsByIfsc.getCITY());
					response.setCenter(fetchBankDetailsByIfsc.getCENTRE());
					response.setDistrict(fetchBankDetailsByIfsc.getDISTRICT());
					response.setSwift(fetchBankDetailsByIfsc.getSWIFT());
					response.setBankName(fetchBankDetailsByIfsc.getBANK());
					response.setBankCode(fetchBankDetailsByIfsc.getBANKCODE());
					response.setIfscCode(fetchBankDetailsByIfsc.getIFSC());
					log.info("############# Executed Logic in getBankDetailsByIfsc() in ServiceImpl Layer ##########");
				}
			}
		} catch (Exception e) {
			log.info("############# Exception Occured in getBankDetailsByIfsc() ServiceImpl Layer ##########" + e);
		}
		return response;
	}

	public List<PostOfficeDetailsResponse> getPostOfficeDetailsByPinCodeOrBranchName(String apiUrl) {
		PinCodeDetailsDTO[] response = null;
		List<PinCodeDTO> reResponse = null;
		List<PostOfficeDetailsResponse> reList = new ArrayList<>();
		String fetchDataFromOtherApi = fetchDataFromOtherApi(apiUrl);
		try {
			log.info(
					"############# Hitting getPostOfficeDetailsByPinCodeOrBranchName() ServiceImpl Layer :: apiUrl ########## "
							+ apiUrl);
			if (fetchDataFromOtherApi != null) {
				response = gson.fromJson(fetchDataFromOtherApi, PinCodeDetailsDTO[].class);
				List<PinCodeDetailsDTO> asList = Arrays.asList(response);
				for (PinCodeDetailsDTO tempObj : asList) {
					reResponse = tempObj.getPostOffice();
				}
			}
			if (reResponse != null) {
				for (PinCodeDTO tempPinCodeDto : reResponse) {
					PostOfficeDetailsResponse tempPostOfficeDetailsResponse = new PostOfficeDetailsResponse();
					tempPostOfficeDetailsResponse.setPostOfficeName(tempPinCodeDto.getName());
					tempPostOfficeDetailsResponse.setDescription(tempPinCodeDto.getDescription());
					tempPostOfficeDetailsResponse.setBranchType(tempPinCodeDto.getBranchType());
					tempPostOfficeDetailsResponse.setDeliveryStatus(tempPinCodeDto.getDeliveryStatus());
					tempPostOfficeDetailsResponse.setCircleName(tempPinCodeDto.getCircle());
					tempPostOfficeDetailsResponse.setDistrictName(tempPinCodeDto.getDistrict());
					tempPostOfficeDetailsResponse.setDivisionName(tempPinCodeDto.getDivision());
					tempPostOfficeDetailsResponse.setRegionName(tempPinCodeDto.getRegion());
					tempPostOfficeDetailsResponse.setBlockName(tempPinCodeDto.getBlock());
					tempPostOfficeDetailsResponse.setState(tempPinCodeDto.getState());
					tempPostOfficeDetailsResponse.setCountry(tempPinCodeDto.getCountry());
					tempPostOfficeDetailsResponse.setPincode(tempPinCodeDto.getPincode());
					reList.add(tempPostOfficeDetailsResponse);
				}
			}
		} catch (Exception e) {
			log.info(
					"############# Exception Occured in getPostOfficeDetailsByPinCodeOrBranchName() ServiceImpl Layer ##########"
							+ e);
		}
		return reList;
	}

	public String fetchDataFromOtherApi(String apiUrl) {
		String body = null;
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "application/json");
		log.info("###### APIURL :: " + apiUrl);
		try {
			ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, new HttpEntity<>(headers),
					String.class);
			body = response.getBody();
			log.info("############# Hitting fetchDataFromOtherApi() ServiceImpl Layer ##########");
		} catch (Exception e) {
			log.info("############# Exception Occured in fetchDataFromOtherApi() ServiceImpl Layer ##########", e);
		}
		return body;
	}

	@Override
	public List<UniversityDetailsDTO> getUniversityDetailsByCountryName(String countryName) {
		List<UniversityDetailsDTO> listUniversity = new ArrayList<>();
		String apiUrl = universityDetailsUrl + "?country=" + countryName;

		try {
			log.info("########## Entered in getUniversityDetailsByCountryName() in ServiceImpl Layer :: apiUrl :: "
					+ apiUrl);
			String fetchDataFromOtherApi = fetchDataFromOtherApi(apiUrl);
			ConsumeUniversityBody[] fromJson = gson.fromJson(fetchDataFromOtherApi, ConsumeUniversityBody[].class);

			listUniversity = Arrays.stream(fromJson).map(p -> {
				UniversityDetailsDTO build = UniversityDetailsDTO.builder().universityName(p.getName())
						.countryName(p.getCountry()).countryCode(p.getAlpha_two_code())
						.stateProvince(p.getStateProvince()).universityWebsite(p.getWeb_pages()[0]).build();
				return build;
			}).distinct().sorted((obj1, obj2) -> obj1.getUniversityName().compareTo(obj2.getUniversityName()))
					.collect(Collectors.toList());
		} catch (Exception e) {
			log.info(
					"########## Exception Occured in getUniversityDetailsByCountryName() in ServiceImpl Layer ########## "
							+ e);
		}
		return listUniversity;
	}

	@Override
	public GuessGenderByNameDTO guessGenderByName(String name) {
		GuessGenderByNameDTO response = null;
		try {
			String apiUrl = guessGenderUrl+name;
			log.info("########## Entered in guessGenderByName() in ServiceImpl Layer :: apiUrl :: "
					+ apiUrl);
			String fetchDataFromOtherApi = fetchDataFromOtherApi(apiUrl);
			response = gson.fromJson(fetchDataFromOtherApi, GuessGenderByNameDTO.class);
		} catch (Exception e) {
			log.info(
					"########## Exception Occured in guessGenderByName() in ServiceImpl Layer ########## "
							+ e);
		}
		return response;
	}
}
