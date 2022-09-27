package com.rest.api.service;

import java.io.IOException;
import java.util.List;

import com.rest.api.entity.ContactDetails;
import com.rest.api.request.WeightSlipRequest;
import com.rest.api.response.MediaFile;
import com.rest.api.response.ReportResponse;

import net.sf.jasperreports.engine.JRException;

public interface ReportService {
	String startReportApi();

	MediaFile exportReport(WeightSlipRequest weightSlipRequest) throws JRException, IOException;

	List<ReportResponse> findAllWeightSlipDetails();

	List<ReportResponse> findByVehicleNumber(String vehicleNumber);

	String deleteAllWeightSlip();

	String sendEmail(ContactDetails contact);
}
