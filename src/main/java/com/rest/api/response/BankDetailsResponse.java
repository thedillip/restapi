package com.rest.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankDetailsResponse {
	private String micrCode;
	private String branchName;
	private String address;
	private String state;
	private String contact;
	private String cityName;
	private String center;
	private String district;
	private String swift;
	private String bankName;
	private String bankCode;
	private String ifscCode;
}
