package com.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.dao.ReportDao;
import com.exam.entity.Report;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private ReportDao rdao;
	
	@Override
	public Report createReport(Report report) {
		return rdao.save(report);
	}

	@Override
	public Report viewReport(int id) {
		return rdao.findById(id);
		
	}

	@Override
	public List<Report> getAll() {
		return rdao.findAll();
	}

}
