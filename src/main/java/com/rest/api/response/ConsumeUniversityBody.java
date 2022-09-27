package com.rest.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumeUniversityBody {
	private String stateprovince;
	private String country;
	private String[] web_pages;
	private String name;
	private String alpha_two_code;
	private String[] domains;
}
