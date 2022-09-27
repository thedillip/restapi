package com.rest.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostOfficeDetailsResponse {
	private String postOfficeName;
	private String description;
	private String branchType;
	private String deliveryStatus;
	private String circleName;
	private String districtName;
	private String divisionName;
	private String regionName;
	private String blockName;
	private String state;
	private String country;
	private String pincode;
}
