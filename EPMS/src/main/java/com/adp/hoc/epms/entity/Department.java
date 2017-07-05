package com.adp.hoc.epms.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

@Entity
public class Department implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long departmentId;
	
	private String departmentName;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Organization organization;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Employee headOfDepartment;
	

	@OneToMany(cascade=CascadeType.ALL)
	@MapKeyColumn(name="mbo_cycle_id")
	private Map<MBOCycle, MBO> allMBOsMap;

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

	public Map<MBOCycle, MBO> getAllMBOsMap() {
		return allMBOsMap;
	}

	public void setAllMBOsMap(Map<MBOCycle, MBO> allMBOsMap) {
		this.allMBOsMap = allMBOsMap;
	}

	public Department() {
		super();
		allMBOsMap = new HashMap<>();
		// TODO Auto-generated constructor stub
	}
	
	//private List<MBO> allMBOs;
	
	
	
	
	
	
	

}
