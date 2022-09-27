package com.rest.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankDetailsDTO {
	private String MICR;
	private String BRANCH;
	private String ADDRESS;
	private String STATE;
	private String CONTACT;
	private boolean UPI;
	private boolean RTGS;
	private String CITY;
	private String CENTRE;
	private String DISTRICT;
	private boolean NEFT;
	private boolean IMPS;
	private String SWIFT;
	private String ISO3166;
	private String BANK;
	private String BANKCODE;
	private String IFSC;
}
