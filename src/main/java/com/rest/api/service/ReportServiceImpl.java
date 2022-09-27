package com.rest.api.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.rest.api.entity.ContactDetails;
import com.rest.api.entity.ReportEntity;
import com.rest.api.repository.ReportEntityRepository;
import com.rest.api.request.WeightSlipRequest;
import com.rest.api.response.MediaFile;
import com.rest.api.response.ReportResponse;
import com.rest.api.util.ProjectConstant;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportEntityRepository repository;

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public String startReportApi() {
		log.info("########## API has been Started :: Status :: UP :: SUCCESS ##########");
		String message = ProjectConstant.SUCCESS_MSG;
		return message;
	}

	@Override
	public MediaFile exportReport(WeightSlipRequest weightSlipRequest) throws JRException, IOException {
		
		log.info("########## Hitting exportReport() method in ServiceImpl Layer ##########");

		String fileName = "Weight Slip_" + weightSlipRequest.getVehicleNumber().toUpperCase() + "_"
				+ ProjectConstant.formattedDateTime(LocalDateTime.now()) + ".pdf";

		if (weightSlipRequest.isChecked()) {
			saveWeightSlipDetails(weightSlipRequest);
			log.info("########## Weight Slip Details Saved Successfully in the PostgresSQL Database ##########");
		}

		List<WeightSlipRequest> list = new ArrayList<>();
		list.add(new WeightSlipRequest());

		String netWeight = String.valueOf(Integer.parseInt(weightSlipRequest.getGrossWeight())
				- Integer.parseInt(weightSlipRequest.getTareWeight()));

		ClassPathResource classPathResource = new ClassPathResource("WeightSlip.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(classPathResource.getInputStream());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("address", weightSlipRequest.getAddress());
		parameters.put("vehicleNumber", weightSlipRequest.getVehicleNumber());
		parameters.put("grossWeight", weightSlipRequest.getGrossWeight());
		parameters.put("tareWeight", weightSlipRequest.getTareWeight());
		parameters.put("netWeight", netWeight);
		parameters.put("grossWeightDate", formattedDate(weightSlipRequest.getGrossWeightDate()));
		parameters.put("tareWeightDate", formattedDate(weightSlipRequest.getTareWeightDate()));
		parameters.put("grossWeightTime", formattedTime(weightSlipRequest.getGrossWeightDate()));
		parameters.put("tareWeightTime", formattedTime(weightSlipRequest.getTareWeightDate()));

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		byte[] data = JasperExportManager.exportReportToPdf(jasperPrint);

		HttpHeaders headers = new HttpHeaders();

		MediaFile mediaFile = new MediaFile();
		mediaFile.setFileName(fileName);
		mediaFile.setByteData(data);

		Date date = new Date();
		headers.set(HttpHeaders.CONTENT_DISPOSITION,
				"attachment;filename=Weight Slip_" + String.valueOf(date) + ".pdf");
		log.info("########## Report Generated in PDF ......... ##########");
		return mediaFile;
	}

	public String formattedDate(LocalDateTime date) {
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return date.format(myFormatObj);
	}

	public String formattedTime(LocalDateTime date) {
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("hh : mm : ss  a");
		return date.format(myFormatObj).toUpperCase();
	}

	public String formattedDateTime(LocalDateTime date) {
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd MMMM yyyy hh:mm:ss a");
		return date.format(myFormatObj).toUpperCase();
	}

	public void saveWeightSlipDetails(WeightSlipRequest weightSlipRequest) {
		ReportEntity entity = new ReportEntity();
		String netWeight = String.valueOf(Integer.parseInt(weightSlipRequest.getGrossWeight())
				- Integer.parseInt(weightSlipRequest.getTareWeight()));
		
		log.info("########## Entered into saveWeightSlipDetails() :: WeightSlipRequest :: "+weightSlipRequest);

		try {
			entity.setAddress(weightSlipRequest.getAddress().toUpperCase());
			entity.setVehicleNumber(weightSlipRequest.getVehicleNumber().toUpperCase());
			entity.setGrossWeight(weightSlipRequest.getGrossWeight());
			entity.setTareWeight(weightSlipRequest.getTareWeight());
			entity.setNetWeight(netWeight);
			entity.setGrossWeightDate(weightSlipRequest.getGrossWeightDate());
			entity.setTareWeightDate(weightSlipRequest.getTareWeightDate());
			entity.setCreatedDate( ProjectConstant.convertUTCtoISTtime(LocalDateTime.now()));
			repository.save(entity);
		} catch (Exception e) {
			log.info("########## Exception Occured While Saving the Weight Slip Details in saveWeightSlipDetails() in ServiceImpl Layer ##########"+e);
		}
	}

	@Override
	public List<ReportResponse> findAllWeightSlipDetails() {
		List<ReportEntity> response = repository.findAll();
		List<ReportResponse> reRespopnse = new ArrayList<>();

		try {
			log.info("########## Hitting findAllWeightSlipDetails() for getting all WeightSlip Details in ServiceImpl Layer ##########");
			for (ReportEntity report : response) {
				ReportResponse obj = new ReportResponse();
				obj.setAddress(report.getAddress());
				obj.setVehicleNumber(report.getVehicleNumber());
				obj.setGrossWeight(report.getGrossWeight()+" KG");
				obj.setTareWeight(report.getTareWeight()+ " KG");
				obj.setNetWeight(report.getNetWeight()+ " KG");
				obj.setGrossWeightDate(formattedDateTime(report.getGrossWeightDate()));
				obj.setTareWeightDate(formattedDateTime(report.getTareWeightDate()));
				obj.setCreatedDate(formattedDateTime(report.getCreatedDate()));

				reRespopnse.add(obj);
			}
		} catch (Exception e) {
			log.info("########## Exception Occured while fetching the Weight Slip Details in findAllWeightSlipDetails() in ServiceImpl Layer ##########"+e);
		}
		return reRespopnse;
	}

	@Override
	public List<ReportResponse> findByVehicleNumber(String vehicleNumber) {
		List<ReportEntity> response = repository.findByVehicleNumber(vehicleNumber);
		List<ReportResponse> reRespopnse = new ArrayList<>();

		try {
			log.info("########## Entered in findByVehicleNumber() in ServiceImpl Layer ##########");
			for (ReportEntity report : response) {
				ReportResponse obj = new ReportResponse();
				obj.setAddress(report.getAddress());
				obj.setVehicleNumber(report.getVehicleNumber());
				obj.setGrossWeight(report.getGrossWeight());
				obj.setTareWeight(report.getTareWeight());
				obj.setNetWeight(report.getNetWeight());
				obj.setGrossWeightDate(formattedDateTime(report.getGrossWeightDate()));
				obj.setTareWeightDate(formattedDateTime(report.getTareWeightDate()));
				obj.setCreatedDate(formattedDateTime(report.getCreatedDate()));

				reRespopnse.add(obj);
			}
		} catch (Exception e) {
			log.info("########## Exception Occured while fetching the Weight Slip Details in findByVehicleNumber() in ServiceImpl Layer ########## "+e);
		}
		return reRespopnse;
	}

	@Override
	public String deleteAllWeightSlip() {
		repository.deleteAll();
		log.info("########## Entered into deleteAllWeightSlip() in ServiceImpl Layer ##########");
		String message = ProjectConstant.SUCCESS_MSG;
		return message;
	}

	@Override
	public String sendEmail(ContactDetails contact) {
		
		log.info("########## Entered into sendEmail() in ServiceImpl Layer ##########");

		String emailBody = "Dear, " + contact.getName() + "\n\n"
				+ "I hope you are having a productive day.\n\nI greatly appreciate the time you spent for visiting my Portfolio.\n\n"
				+ "Thank you for sharing your valuable feedback - Keep in Touch"
				+ "\n\nNOTE: This is an auto generated mail. Please do not reply to this message or on this email address.\n\n"
				+ "Thanks & Regards \nDillip K Sahoo\nContact Number :- +91 8117941692\nMailto:- lit.dillip2017@gmail.com\nWebsite:- https://dillipfolio.web.app";

		String subject = "Welcome to DillipFolio – Thanks for Visiting !!";
		
		log.info("########## Email Body ########## :: Email Content :: "+emailBody);

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("dongjinmaster9@gmail.com");
		message.setTo(contact.getEmail());
		message.setText(emailBody);
		message.setSubject(subject);

		mailSender.send(message);

		log.info("########## Mail has been send Successfully :: SUCCESS ##########");

		ContactDetails contactDetails = new ContactDetails();
		contactDetails.setName(contact.getName());
		contactDetails.setEmail(contact.getEmail());
		contactDetails.setMessage(contact.getMessage());
		contactDetails.setSubject(contact.getSubject());

		return ProjectConstant.SUCCESS_MSG;
	}
}
