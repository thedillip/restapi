package com.rest.api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rest.api.response.EmployeeResponse;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<EmployeeResponse> getAllEmployee() {
		final EmployeeResponse[] tempEmployee = { null };
		List<EmployeeResponse> employeeResponseList = new ArrayList<>();

		String firstName = "Dillip";
		
		int id = 1;

		String sqlQuery = "select id,first_name,company_name,email from employee where first_name = ?";
		
		String sqlQuery2 = "select id as id,first_name as firstName,company_name as companyName,email as email from employee where id = ?";

//		jdbcTemplate.query(sqlQuery, new RowMapper<List<EmployeeResponse>>() {
//
//			@Override
//			public List<EmployeeResponse> mapRow(ResultSet rs, int rowNum) throws SQLException {
//				
//				tempEmployee[0] = new EmployeeResponse();
//				tempEmployee[0].setId(rs.getInt("id"));
//				tempEmployee[0].setFirstName(rs.getString("first_name"));
//				tempEmployee[0].setCompanyName(rs.getString("company_name"));
//				tempEmployee[0].setEmail(rs.getString("email"));
//				
//				employeeResponseList.add(tempEmployee[0]);
//				return employeeResponseList;
//			}
//		});

		
		
//		RowMapper<List<EmployeeResponse>> rowMapper = (rs, rowNum) -> {
//			
//			tempEmployee[0] = EmployeeResponse.builder().id(rs.getInt("eid")).firstName(rs.getString("name"))
//					.companyName(rs.getString("tcs")).email(rs.getString("myemail")).build();
//			tempEmployee[0].setId(rs.getInt("id"));
//			tempEmployee[0].setFirstName(rs.getString("first_name"));
//			tempEmployee[0].setCompanyName(rs.getString("company_name"));
//			tempEmployee[0].setEmail(rs.getString("email"));
//			employeeResponseList.add(tempEmployee[0]);
//			return employeeResponseList;
//		};
		
//		RowMapper<List<EmployeeResponse>> rowMapper2 = (rs, rowNum) -> {
//			EmployeeResponse build = EmployeeResponse.builder().id(rs.getInt("eid")).firstName(rs.getString("name"))
//			.companyName(rs.getString("tcs")).email(rs.getString("myemail")).build();
//			employeeResponseList.add(build);
//			return employeeResponseList;
//		};
		
		RowMapper<EmployeeResponse> rowMapper2 = (rs, rowNum) -> {
			EmployeeResponse build = EmployeeResponse.builder().id(rs.getInt("eid")).firstName(rs.getString("name"))
			.companyName(rs.getString("tcs")).email(rs.getString("myemail")).build();
			
			System.out.println(build);
			
//			employeeResponseList.add(build);
			return build;
		};

//		jdbcTemplate.query(sqlQuery2, new Object[] { id }, new int[] { Types.INTEGER }, rowMapper2);
		
		EmployeeResponse queryForObject = jdbcTemplate.queryForObject(sqlQuery2, new Object[] { id }, new int[] { Types.INTEGER }, EmployeeResponse.class);
		
		System.out.println(queryForObject);
		
//		String sqlQuery3 = "select count(*) as total from employee";
//		Integer queryForObject = jdbcTemplate.queryForObject(sqlQuery3, Integer.class);
//		System.out.println("Total number of records = "+queryForObject);

		return employeeResponseList;
	}

}
