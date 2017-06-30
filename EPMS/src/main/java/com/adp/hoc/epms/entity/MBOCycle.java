package com.adp.hoc.epms.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class MBOCycle implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long mboCycleId;
	
	@OneToOne(cascade=CascadeType.ALL)
	Organization organization;
	
	private long effectiveFrom;
	private long effectiveTo;
	
	@OneToOne(cascade=CascadeType.ALL)
	private WeightedMeasurable organizationalWeigtedMeasurableInDepartmentalMBO;
	
	private double departmentalWeightInEmployeeMBO;

	public WeightedMeasurable getOrganizationalWeigtedMeasurableInDepartmentalMBO() {
		return organizationalWeigtedMeasurableInDepartmentalMBO;
	}


	public void setOrganizationalWeigtedMeasurableInDepartmentalMBO(
			WeightedMeasurable organizationalWeigtedMeasurableInDepartmentalMBO) {
		this.organizationalWeigtedMeasurableInDepartmentalMBO = organizationalWeigtedMeasurableInDepartmentalMBO;
	}
	

	public MBOCycle(Organization organization, long from, long to, double orgPercentageInDepartment, double departmentPercentageInEmployees) {
		super();
		this.organization = organization;
		this.effectiveFrom = from;
		this.effectiveTo = to;
		
		this.departmentalWeightInEmployeeMBO = departmentPercentageInEmployees;
		
		WeightedMeasurable orgToDep = new WeightedMeasurable();
		orgToDep.setWeight(orgPercentageInDepartment);
		orgToDep.setMeasurable(new Measurable());
		this.organizationalWeigtedMeasurableInDepartmentalMBO = orgToDep;
	}


	protected MBOCycle() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getMboCycleId() {
		return mboCycleId;
	}


	public void setMboCycleId(long mboCycleId) {
		this.mboCycleId = mboCycleId;
	}


	public Organization getOrganization() {
		return organization;
	}


	public void setOrganization(Organization organization) {
		this.organization = organization;
	}


	public long getEffectiveFrom() {
		return effectiveFrom;
	}


	public void setEffectiveFrom(long effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}


	public long getEffectiveTo() {
		return effectiveTo;
	}


	public void setEffectiveTo(long effectiveTo) {
		this.effectiveTo = effectiveTo;
	}


	public double getDepartmentalWeightInEmployeeMBO() {
		return departmentalWeightInEmployeeMBO;
	}


	public void setDepartmentalWeightInEmployeeMBO(double departmentalWeightInEmployeeMBO) {
		this.departmentalWeightInEmployeeMBO = departmentalWeightInEmployeeMBO;
	}
	
	

}
