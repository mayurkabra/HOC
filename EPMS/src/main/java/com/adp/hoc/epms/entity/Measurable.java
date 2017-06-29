package com.adp.hoc.epms.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Measurable implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long measurableMBOId;
	private String description;
	private double scoreNumerator;
	private double scoreDenominator;
	
	
	
	public Measurable(String description, double scoreNumerator, double scoreDenominator) {
		super();
		this.description = description;
		this.scoreNumerator = scoreNumerator;
		this.scoreDenominator = scoreDenominator;
	}
	
	public Measurable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double calculateScoreOutOfUnity(){
		if(scoreDenominator > 0){
			return scoreNumerator/scoreDenominator;
		}
		return 0;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getScoreNumerator() {
		return scoreNumerator;
	}
	public void setScoreNumerator(double scoreNumerator) {
		this.scoreNumerator = scoreNumerator;
	}
	public double getScoreDenominator() {
		return scoreDenominator;
	}
	public void setScoreDenominator(double scoreDenominator) {
		this.scoreDenominator = scoreDenominator;
	}
	public long getMeasurableMBOId() {
		return measurableMBOId;
	}
	public void setMeasurableMBOId(long measurableMBOId) {
		this.measurableMBOId = measurableMBOId;
	}
	
	

}
