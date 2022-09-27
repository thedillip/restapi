package com.rest.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.api.entity.ReportEntity;

public interface ReportEntityRepository extends JpaRepository<ReportEntity, Integer> {
	public List<ReportEntity> findByVehicleNumber(String vehicleNumber);
}
