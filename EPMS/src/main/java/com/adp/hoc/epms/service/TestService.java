package com.adp.hoc.epms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adp.hoc.epms.dao.TestDAO;
import com.adp.hoc.epms.entity.Organization;

@Service
public class TestService {
	
	@Autowired
	TestDAO dao;

	public void addOrganizationTest(String companyName, Date date) {
		dao.addOrganizationTest(companyName, date);
	}

}
