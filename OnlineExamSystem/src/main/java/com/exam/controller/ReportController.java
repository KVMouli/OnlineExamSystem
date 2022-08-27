package com.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entity.Report;
import com.exam.service.ReportService;

@RestController
@RequestMapping("/report")
public class ReportController {
	@Autowired
	private ReportService rserv;
	
	
	
	@PostMapping("/add")
	public Report createReport(@RequestBody Report report){
		rserv.createReport(report);
		return report;
	}
	
	@GetMapping("/{id}")
	public Report showReport(@PathVariable int id){
		Report rlist = rserv.viewReport(id);
		return rlist;
	}
	
	@GetMapping("/showAll")
	public List<Report> showAllReport(){
		List<Report> rlist = rserv.getAll();
		return rlist;
	}
	
	
	
}
