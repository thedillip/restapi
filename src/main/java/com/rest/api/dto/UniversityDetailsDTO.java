package com.rest.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UniversityDetailsDTO {
	private String universityName;
	private String countryName;
	private String countryCode;
	private String stateProvince;
	private String universityWebsite;
}
