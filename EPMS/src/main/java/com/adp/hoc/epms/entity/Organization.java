package com.adp.hoc.epms.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

@Entity
public class Organization implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orgId;
	private String orgName;

	@OneToMany(cascade=CascadeType.ALL)
	@MapKeyColumn(name="mbo_cycle_id")
	private Map<MBOCycle, MBO> allMBOsMap;

	
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Map<MBOCycle, MBO> getAllMBOsMap() {
		return allMBOsMap;
	}
	public void setAllMBOsMap(Map<MBOCycle, MBO> allMBOsMap) {
		this.allMBOsMap = allMBOsMap;
	}
	public Organization() {
		super();
		this.allMBOsMap = new HashMap<>();
	}
	
	
	

}
