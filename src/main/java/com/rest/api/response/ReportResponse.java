package com.rest.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportResponse {
	private String address;
	private String vehicleNumber;
	private String grossWeight;
	private String tareWeight;
	private String netWeight;
	private String grossWeightDate;
	private String tareWeightDate;
	private String createdDate;
}
