package com.adp.hoc.epms.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class WeightedMeasurable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long weightedMeasurableId;
	
	private double weight;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Measurable measurable;
	
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public Measurable getMeasurable() {
		return measurable;
	}
	public void setMeasurable(Measurable measurable) {
		this.measurable = measurable;
	}
	public long getWeightedMeasurableId() {
		return weightedMeasurableId;
	}
	public void setWeightedMeasurableId(long weightedMeasurableId) {
		this.weightedMeasurableId = weightedMeasurableId;
	}
	
	

}
