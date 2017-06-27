package com.adp.hoc.epms.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adp.hoc.epms.entity.Organization;
import com.adp.hoc.epms.service.TestService;

@RestController
@RequestMapping(value="test/")
public class TestController {
	
	@Autowired
	TestService testService;
	
	@RequestMapping(value="test")
	public String test(){
		return "successfull";
	}
	
	@RequestMapping(value="testHibernate")
	public void testHibernate(){
		
		//testService.addOrganizationTest("ADP", new Date());
	}

}
