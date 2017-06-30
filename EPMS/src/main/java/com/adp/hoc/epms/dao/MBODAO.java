package com.adp.hoc.epms.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.adp.hoc.epms.entity.MBOCycle;
import com.adp.hoc.epms.entity.Organization;
import com.adp.hoc.epms.utility.HibernateUtil;

@Repository
public class MBODAO {

	@Transactional
	public MBOCycle createMBOCycle(Organization organization, long from, long to, double orgPercentageInDepartment, double departmentPercentageInEmployees) {
		MBOCycle cycle = new MBOCycle(organization, from, to, orgPercentageInDepartment, departmentPercentageInEmployees);
		Session currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
		return (MBOCycle) currentSession.save(cycle);
	}

}
