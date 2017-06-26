package com.adp.hoc.epms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long departmentId;
	
	private String departmentName;
	
	private Organization organization;
	
	private Employee headOfDepartment;
	
	//private List<MBO> allMBOs;
	
	

}
