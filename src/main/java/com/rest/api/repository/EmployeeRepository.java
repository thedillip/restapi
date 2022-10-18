package com.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.api.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
