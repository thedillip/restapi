package com.rest.api.dao;

import java.util.List;

import com.rest.api.response.EmployeeResponse;

public interface EmployeeDAO {
	List<EmployeeResponse> getAllEmployee();
}
