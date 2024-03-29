package com.rest.api.service;

import java.util.List;

import com.rest.api.entity.Employee;
import com.rest.api.request.EmployeeRequest;
import com.rest.api.response.EmployeeResponse;

public interface EmployeeService {
	Employee createEmployee(EmployeeRequest employeeRequest);
	List<Employee> getAllEmployees();
	Employee getEmployeeById(int id);
	String updateEmployeeById(EmployeeRequest employeeRequest);
	String deleteAllEmployees();
	String deleteEmployeeById(int id);
	
	List<EmployeeResponse> getAllEmployeeWithJdbcTemplate();
}
