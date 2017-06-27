package com.adp.hoc.epms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

@Entity
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long departmentId;
	
	private String departmentName;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Organization organization;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Employee headOfDepartment;

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Employee getHeadOfDepartment() {
		return headOfDepartment;
	}

	public void setHeadOfDepartment(Employee headOfDepartment) {
		this.headOfDepartment = headOfDepartment;
	}
	
	//private List<MBO> allMBOs;
	
	
	
	
	
	
	

}
