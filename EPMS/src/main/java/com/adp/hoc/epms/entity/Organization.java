package com.adp.hoc.epms.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Organization implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orgId;
	private String orgName;
	private long lastMBORefreshDate;

	
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public long getLastMBORefreshDate() {
		return lastMBORefreshDate;
	}
	public void setLastMBORefreshDate(long lastMBORefreshDate) {
		this.lastMBORefreshDate = lastMBORefreshDate;
	}
	
	

}
