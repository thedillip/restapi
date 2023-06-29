package com.rest.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponse {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String gender;
	private String country;
	private String state;
	private String city;
	private String companyName;
}
