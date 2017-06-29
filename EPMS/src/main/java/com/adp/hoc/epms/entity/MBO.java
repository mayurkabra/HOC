package com.adp.hoc.epms.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "MBO")
public class MBO extends Measurable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	private MBOType mboType;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<WeightedMeasurable> weightedMeasurables = new ArrayList<>();
	
	private long effectiveFrom;
	
	private long effectiveTill;

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

	public long getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(long effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public long getEffectiveTill() {
		return effectiveTill;
	}

	public void setEffectiveTill(long effectiveTill) {
		this.effectiveTill = effectiveTill;
	}
	
	public void recalculateScore(){
		this.setWeightedMeasurables(this.getWeightedMeasurables());
	}

}
