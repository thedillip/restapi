package com.rest.api.entity;

import java.time.LocalDateTime;

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
@Table(name = "tbl_report_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sl_no")
	private int reportSlNo;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "vehicle_number")
	private String vehicleNumber;
	
	@Column(name = "gross_weight")
	private String grossWeight;
	
	@Column(name = "tare_weight")
	private String tareWeight;
	
	@Column(name = "net_weight")
	private String netWeight;
	
	@Column(name = "gross_weight_date")
	private LocalDateTime grossWeightDate;
	
	@Column(name = "tare_weight_date")
	private LocalDateTime tareWeightDate;
	
	@Column(name = "created_date")
	private LocalDateTime createdDate;
}
