package com.adp.hoc.epms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;

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
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="manager_id")
	private Employee manager;
	
	@Enumerated(EnumType.STRING)
	private JobLevel jobLevel;
	
	private String jobTitle;
	
	private long joiningDate;
	
	//private List<MBO> allMBOs;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Department department;

	public long getEmployeeDBId() {
		return employeeDBId;
	}

	public void setEmployeeDBId(long employeeDBId) {
		this.employeeDBId = employeeDBId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public JobLevel getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(JobLevel jobLevel) {
		this.jobLevel = jobLevel;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public long getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(long joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Employee(String firstName, String middleName, String lastName, JobLevel jobLevel, String jobTitle, long joiningDate) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.jobLevel = jobLevel;
		this.jobTitle = jobTitle;
		this.joiningDate = joiningDate;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	
	

}
