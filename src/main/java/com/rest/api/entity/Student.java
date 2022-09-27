package com.rest.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

	@Column(name = "student_name")
	private String studentName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roll_no")
	private int rollNo;

	@Column(name = "mobile_number")
	private long mobileNumber;

	private int age;

	private String gender;
}
