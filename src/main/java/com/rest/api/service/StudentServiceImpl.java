package com.rest.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.api.dto.StudentDTO;
import com.rest.api.entity.Student;
import com.rest.api.exception.ResourceNotFoundException;
import com.rest.api.repository.StudentRepository;
import com.rest.api.request.StudentRequest;
import com.rest.api.util.ProjectConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public String addStudent(StudentRequest student) {

		Student reStudent = null;
		String message = ProjectConstant.ERR_MSG;

		try {
			reStudent = new Student();
			reStudent.setStudentName(student.getName());
			reStudent.setAge(student.getAge());
			reStudent.setGender(student.getGender());
			reStudent.setMobileNumber(student.getMobileNumber());
			studentRepository.save(reStudent);
			log.info("################ Student Record has been added Successfully ##############");
			message = ProjectConstant.SUCCESS_MSG;
		} catch (Exception e) {
			log.info("############# Exception Occured ##########", e);
		}

		return message;
	}

	@Override
	public List<StudentDTO> findAllStudentDetails() {

		List<Student> student = null;
		List<StudentDTO> retList = new ArrayList<>();

		try {
			student = studentRepository.findAll();
			for (Student tempStudent : student) {
				StudentDTO tempStudentDto = new StudentDTO();
				tempStudentDto.setStudentName(tempStudent.getStudentName());
				tempStudentDto.setMobileNumber(tempStudent.getMobileNumber());
				tempStudentDto.setGender(tempStudent.getGender());
				tempStudentDto.setAge(tempStudent.getAge());
				tempStudentDto.setRollNumber(tempStudent.getRollNo());
				retList.add(tempStudentDto);
			}
		} catch (Exception e) {
			log.info("############# Exception Occured ##########", e);
		}
		retList.sort((studentObj1, studentObj2) -> (studentObj1.getRollNumber() < studentObj2.getRollNumber()) ? -1
				: (studentObj1.getRollNumber() > studentObj2.getRollNumber()) ? +1 : 0);
		return retList;
	}

	@Override
	public StudentDTO findStudentDetailsByRollNumber(int rollNo) {
		Optional<Student> student = null;
		StudentDTO reStudent = null;

//		try {
			student = studentRepository.findById(rollNo);
			if (student.isPresent()) {
				Student tempStudent = student.get();
				reStudent = new StudentDTO();
				reStudent.setStudentName(tempStudent.getStudentName());
				reStudent.setMobileNumber(tempStudent.getMobileNumber());
				reStudent.setGender(tempStudent.getGender());
				reStudent.setAge(tempStudent.getAge());
				reStudent.setRollNumber(tempStudent.getRollNo());
			}else {
				throw new ResourceNotFoundException("Student not found in the server with the rollNo "+rollNo);
			}
//		} catch (Exception e) {
//			log.info("############# Exception Occured ##########", e);
//		}
		return reStudent;
	}

	@Override
	public String updateStudentDetailsByRollNumber(int rollNo, StudentRequest studentRequest) {
		String message = null;
		Optional<Student> student = null;
		try {
			student = studentRepository.findById(rollNo);
			if (student.isPresent()) {
				Student tempStudent = student.get();
				tempStudent.setStudentName(studentRequest.getName());
				tempStudent.setMobileNumber(studentRequest.getMobileNumber());
				tempStudent.setGender(studentRequest.getGender());
				tempStudent.setAge(studentRequest.getAge());
				studentRepository.save(tempStudent);
				log.info("############# Student Resource has been Updated Successfully ##########");
				message = ProjectConstant.SUCCESS_MSG;
			} else
				message = ProjectConstant.NOT_PRESENT;

		} catch (Exception e) {
			log.info("############# Exception Occured ##########", e);
			message = ProjectConstant.ERR_MSG;
		}
		return message;
	}

	@Override
	public String deleteStudentByRollNumber(int rollNo) {
		String message = null;
		Optional<Student> student = null;

		try {
			student = studentRepository.findById(rollNo);
			if (student.isPresent()) {
				Student tempStudent = student.get();
				studentRepository.deleteById(tempStudent.getRollNo());
				log.info("############# Student Resource has been Deleted Successfully ##########");
				message = ProjectConstant.SUCCESS_MSG;
			} else
				message = ProjectConstant.NOT_PRESENT;
		} catch (Exception e) {
			log.info("############# Exception Occured ##########", e);
			message = ProjectConstant.ERR_MSG;
		}
		return message;
	}

	@Override
	public String deleteAllStudent() {
		String message = null;
		try {
			List<Student> student = studentRepository.findAll();
			if (!student.isEmpty()) {
				studentRepository.deleteAll();
				log.info("############# All Student Resource has been Deleted Successfully ##########");
				message = ProjectConstant.SUCCESS_MSG;
			} else
				message = ProjectConstant.NOT_PRESENT;
		} catch (Exception e) {
			log.info("############# Exception Occured ##########", e);
			message = ProjectConstant.ERR_MSG;
		}
		return message;
	}
}
