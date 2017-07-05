package com.adp.hoc.epms.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "MBO")
public class MBO extends Measurable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	private MBOType mboType;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="mbo_cycle_id")
	private MBOCycle mboCycle;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<WeightedMeasurable> weightedMeasurables = new ArrayList<>();

	public MBO(MBOType mboType, MBOCycle mboCycle) {
		super();
		this.mboType = mboType;
		this.mboCycle = mboCycle;
		
		if(mboType.equals(MBOType.ORGANIZATION_MBO)){
			mboCycle.getOrganizationalWeigtedMeasurableInDepartmentalMBO().setMeasurable(this);
		} else if(mboType.equals(MBOType.DEPARTMENT_MBO)){
			this.addWeightedMeasurable(mboCycle.getOrganizationalWeigtedMeasurableInDepartmentalMBO());
		} /*else if(mboType.equals(MBOType.EMPLOYEE_MBO)){
			WeightedMeasurable weightedMeasurable = new WeightedMeasurable();
			weightedMeasurable.setMeasurable(new Measurable());
			weightedMeasurable.setWeight(mboCycle.getDepartmentalWeightInEmployeeMBO());
		}*/
	}
	
	public MBO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public MBO(String description, double scoreNumerator, double scoreDenominator) {
		super(description, scoreNumerator, scoreDenominator);
		// TODO Auto-generated constructor stub
	}



	public List<WeightedMeasurable> getWeightedMeasurables() {
		return weightedMeasurables;
	}

	public void setWeightedMeasurables(List<WeightedMeasurable> newWeightedMeasurables) {
		this.setScoreDenominator(0);
		this.setScoreNumerator(0);
		this.weightedMeasurables = new ArrayList<>();
		for (WeightedMeasurable weightedMeasurable : newWeightedMeasurables) {
			this.addWeightedMeasurable(weightedMeasurable);
		}
	}
	
	public void addWeightedMeasurable(WeightedMeasurable weightedMeasurables){
		this.weightedMeasurables.add(weightedMeasurables);
		//weightedMeasurables.setMbo(this);
		double numerator = weightedMeasurables.getMeasurable().getScoreNumerator();
		double denominator = weightedMeasurables.getMeasurable().getScoreDenominator();
		double weight = weightedMeasurables.getWeight();
		
		this.setScoreNumerator(this.getScoreNumerator()+((numerator/denominator)*weight));
		this.setScoreDenominator(this.getScoreDenominator()+weight);
	}

	public MBOType getMboType() {
		return mboType;
	}

	public void setMboType(MBOType mboType) {
		this.mboType = mboType;
	}
	
	public void recalculateScore(){
		this.setWeightedMeasurables(this.getWeightedMeasurables());
	}

	public MBOCycle getMboCycle() {
		return mboCycle;
	}

	public void setMboCycle(MBOCycle mboCycle) {
		this.mboCycle = mboCycle;
	}

}
