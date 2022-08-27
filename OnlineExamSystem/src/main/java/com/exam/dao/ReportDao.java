package com.exam.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.entity.Report;

@Repository
public interface ReportDao extends JpaRepository<Report, Integer>{
	public Report findById(int id); 
}
