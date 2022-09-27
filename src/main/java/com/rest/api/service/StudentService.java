package com.rest.api.service;

import java.util.List;

import com.rest.api.dto.StudentDTO;
import com.rest.api.request.StudentRequest;

public interface StudentService {
	
	String addStudent(StudentRequest student);
	
	List<StudentDTO> findAllStudentDetails();
	
	StudentDTO findStudentDetailsByRollNumber(int rollNo);
	
	String updateStudentDetailsByRollNumber(int rollNo,StudentRequest student);
	
	String deleteStudentByRollNumber(int rollNo);
	
	String deleteAllStudent();
	
}
