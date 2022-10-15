package com.rest.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.dto.StudentDTO;
import com.rest.api.request.StudentRequest;
import com.rest.api.response.ApiEntity;
import com.rest.api.response.ApiResponseObject;
import com.rest.api.service.StudentService;
import com.rest.api.util.ProjectConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Operation(summary = "saveStudentDetails")
	@PostMapping(path = "/student", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ApiResponseObject> addStudent(
			@Parameter(name = "in_StudentRequest", description = "StudentRequest", required = true) @RequestBody StudentRequest studentRequest) {

		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		String response = null;
		log.info("############# addStudent() :: StudentRequest :: " + studentRequest);
		try {
			response = studentService.addStudent(studentRequest);
			if (response.equals(ProjectConstant.SUCCESS_MSG)) {
				message = ProjectConstant.CREATED_MSG;
				status = HttpStatus.CREATED;
			} else {
				message = "Sorry ! An Error Occured While Saving the Resource";
				status = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception e) {
			log.info("############# Exception Occured ##########", e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(new ApiEntity<String>(message, response), httpHeaders, status);
	}

	@Operation(summary = "getAllStudentsDetails")
	@GetMapping(path = "/student", produces = "application/json")
	public ResponseEntity<ApiResponseObject> findAllStudentDetails() {
		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		List<StudentDTO> response = null;

		try {
			response = studentService.findAllStudentDetails();
			if (!response.isEmpty()) {
				message = ProjectConstant.DATA_FOUND;
				status = HttpStatus.OK;
			} else {
				message = ProjectConstant.DATA_NOT_FOUND;
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			log.info("############# Exception Occured ##########", e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(new ApiEntity<List<StudentDTO>>(message, response), httpHeaders, status);
	}

	@Operation(summary = "getStudentDetailsByRollNo")
	@GetMapping(path = "/student/{roll_no}", produces = "application/json")
	public ResponseEntity<ApiResponseObject> findStudentDetailsByRollNumber(
			@Parameter(description = "Integer", required = true) @PathVariable(name = "roll_no", required = true) int rollNo) {
		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		StudentDTO response = null;
		log.info("############# findStudentDetailsByRollNumber() :: roll_no :: " + rollNo);
		try {
			response = studentService.findStudentDetailsByRollNumber(rollNo);
			if (response != null) {
				status = HttpStatus.OK;
				message = ProjectConstant.DATA_FOUND;
			} else {
				status = HttpStatus.NOT_FOUND;
				message = ProjectConstant.DATA_NOT_FOUND;
			}
		} catch (Exception e) {
			log.info("############# Exception Occured ##########", e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(new ApiEntity<StudentDTO>(message, response), httpHeaders, status);
	}

	@Operation(summary = "updateStudentDetailsByRollNo")
	@PutMapping(path = "/student/{roll_no}")
	public ResponseEntity<ApiResponseObject> updateStudentDetailsByRollNumber(
			@Parameter(description = "Integer", required = true) @PathVariable(name = "roll_no", required = true) int rollNo,
			@Parameter(name = "in_StudentRequest", description = "StudentRequest", required = true) @RequestBody(required = true) StudentRequest request) {
		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		String response = null;
		log.info("############# updateStudentDetailsByRollNumber() :: roll_no :: " + rollNo
				+ " :: StudentRequest :: " + request);
		try {
			response = studentService.updateStudentDetailsByRollNumber(rollNo, request);
			if (response.equals(ProjectConstant.SUCCESS_MSG)) {
				status = HttpStatus.OK;
				message = ProjectConstant.UPDATED_MSG;
			} else if (response.equals(ProjectConstant.NOT_PRESENT)) {
				status = HttpStatus.NOT_FOUND;
				message = ProjectConstant.RESOURCE_NOT_PRESENT;
			}
		} catch (Exception e) {
			log.info("############# Exception Occured ##########", e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(new ApiEntity<String>(message, response), httpHeaders, status);

	}

	@Operation(summary = "deleteStudentDetailsByRollNo")
	@DeleteMapping(path = "/student/{roll_no}")
	public ResponseEntity<ApiResponseObject> deleteStudentByRollNumber(
			@Parameter(description = "Integer", required = true) @PathVariable(name = "roll_no", required = true) int rollNo) {
		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		String response = null;
		log.info("############# deleteStudentByRollNumber() :: roll_no :: " + rollNo);
		try {
			response = studentService.deleteStudentByRollNumber(rollNo);
			if (response.equals(ProjectConstant.SUCCESS_MSG)) {
				status = HttpStatus.OK;
				message = ProjectConstant.DELETED_MSG;
			} else if (response.equals(ProjectConstant.NOT_PRESENT))
				;
			{
				status = HttpStatus.NOT_FOUND;
				message = ProjectConstant.RESOURCE_NOT_PRESENT;
			}
		} catch (Exception e) {
			log.info("############# Exception Occured ##########", e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(new ApiEntity<String>(message, response), httpHeaders, status);
	}

	@Operation(summary = "deleteAllStudentDetails")
	@DeleteMapping(path = "/student")
	public ResponseEntity<ApiResponseObject> deleteAllStudent() {
		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		String response = null;
		try {
			response = studentService.deleteAllStudent();
			if (response.equals(ProjectConstant.SUCCESS_MSG)) {
				status = HttpStatus.OK;
				message = ProjectConstant.DELETED_MSG;
			} else {
				status = HttpStatus.NOT_FOUND;
				message = ProjectConstant.RESOURCE_NOT_PRESENT;
				response = ProjectConstant.NOT_PRESENT;
			}
		} catch (Exception e) {
			log.info("############# Exception Occured ##########", e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(new ApiEntity<String>(message, response), httpHeaders, status);
	}
}
