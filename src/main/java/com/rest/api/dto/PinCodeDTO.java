package com.rest.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PinCodeDTO {
	private String Name;
	private String Description;
	private String BranchType;
	private String DeliveryStatus;
	private String Circle;
	private String District;
	private String Division;
	private String Region;
	private String Block;
	private String State;
	private String Country;
	private String Pincode;
}
