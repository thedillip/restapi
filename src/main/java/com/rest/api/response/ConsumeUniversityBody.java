package com.rest.api.response;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumeUniversityBody {
	@SerializedName(value = "state-province")
	private String stateProvince;
	private String country;
	private String[] web_pages;
	private String name;
	private String alpha_two_code;
	private String[] domains;
}
