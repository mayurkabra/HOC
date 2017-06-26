package com.adp.hoc.epms.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employeeDBId;
	
	@NotNull
	private String firstName;
	
	private String middleName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private Employee manager;
	
	@Enumerated(EnumType.STRING)
	private JobLevel jobLevel;
	
	private long joiningDate;
	
	//private List<MBO> allMBOs;
	
	private Department department;

}
