package com.adp.hoc.epms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adp.hoc.epms.dao.MBOCRUDRepository;
import com.adp.hoc.epms.dao.MBODAO;
import com.adp.hoc.epms.entity.MBOCycle;
import com.adp.hoc.epms.entity.Organization;

@Service
public class MBOService {
	
	@Autowired
	MBODAO mbodao;
	
	/*@Autowired
	MBOCRUDRepository mbodao;*/
	
	public MBOCycle createMBOCycle(Organization organization, long from, long to, double orgPercentageInDepartment, double departmentPercentageInEmployees){
		return mbodao.createMBOCycle(organization, from, to, orgPercentageInDepartment, departmentPercentageInEmployees);
		/*MBOCycle cycle = new MBOCycle(organization, from, to, orgPercentageInDepartment, departmentPercentageInEmployees);
		return mbodao.save(cycle);*/
	}

}
