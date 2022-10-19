package com.rest.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryDetailsResponse {
	private String name;
	private String code;
	private String capital;
	private String currency;
	private String phone;
}
