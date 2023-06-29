package com.rest.api.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.api.dao.EmployeeDAO;
import com.rest.api.entity.Employee;
import com.rest.api.repository.EmployeeRepository;
import com.rest.api.request.EmployeeRequest;
import com.rest.api.response.EmployeeResponse;
import com.rest.api.util.ProjectConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	public Employee createEmployee(EmployeeRequest employeeRequest) {
		Employee employee = modelMapper.map(employeeRequest, Employee.class);
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		return optionalEmployee.orElse(null);
	}

	@Override
	public String updateEmployeeById(EmployeeRequest employeeRequest) {
		Optional<Employee> optionalEmployee = null;
		try {
			optionalEmployee = employeeRepository.findById(employeeRequest.getId());
			if(optionalEmployee.isPresent()) {
				Employee employee = optionalEmployee.get();
				employee.setFirstName(employeeRequest.getFirstName());
				employee.setLastName(employeeRequest.getLastName());
				employee.setEmail(employeeRequest.getEmail());
				employee.setPhone(employeeRequest.getPhone());
				employee.setGender(employeeRequest.getGender());
				employee.setCountry(employeeRequest.getCountry());
				employee.setState(employeeRequest.getState());
				employee.setCity(employeeRequest.getCity());
				employee.setCompanyName(employeeRequest.getCompanyName());
				
				Employee updatedEmployee = employeeRepository.save(employee);
				if(updatedEmployee != null)
					return ProjectConstant.UPDATED_MSG;
			}
		} catch (Exception e) {
			log.info("########## Exception Occured While Updating the Employee Details. ########## "+e);
		}
		return null;
	}

	@Override
	public String deleteAllEmployees() {
		employeeRepository.deleteAll();
		return ProjectConstant.DELETED_MSG;
	}

	@Override
	public String deleteEmployeeById(int id) {
		employeeRepository.deleteById(id);
		return ProjectConstant.DELETED_MSG;
	}

	@Override
	public List<EmployeeResponse> getAllEmployeeWithJdbcTemplate() {
		return employeeDAO.getAllEmployee();
	}

}
