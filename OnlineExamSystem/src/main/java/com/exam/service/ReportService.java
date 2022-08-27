package com.exam.service;

import java.util.List;

import com.exam.entity.Report;

public interface ReportService {
	public Report createReport(Report report);
	public Report viewReport(int id);
	public List<Report> getAll();
}
