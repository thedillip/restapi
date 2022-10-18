package com.rest.api.graphqlquery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.rest.api.entity.Employee;
import com.rest.api.request.EmployeeRequest;
import com.rest.api.service.EmployeeService;

@Controller
public class EmployeeQuery {
	@Autowired
	private EmployeeService employeeService;

	@QueryMapping(value = "employees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@QueryMapping(value = "employee")
	public Employee getEmployeeById(@Argument(name = "id") int id) {
		return employeeService.getEmployeeById(id);
	}

	@MutationMapping(value = "createEmployee")
	public Employee createEmployee(@Argument(name = "employeeRequest") EmployeeRequest employeeRequest) {
		return employeeService.createEmployee(employeeRequest);
	}

	@MutationMapping(value = "updateEmployeeById")
	public String updateEmployeeById(@Argument(name = "employeeRequest") EmployeeRequest employeeRequest) {
		return employeeService.updateEmployeeById(employeeRequest);
	}

	@MutationMapping(value = "deleteAllEmployees")
	public String deleteAllEmployees() {
		return employeeService.deleteAllEmployees();
	}

	@MutationMapping(value = "deleteEmployeeById")
	public String deleteEmployeeById(@Argument(name = "id") int id) {
		return employeeService.deleteEmployeeById(id);
	}
}
