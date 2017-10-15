package com.adp.hoc.epms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.adp.hoc.epms.utility.HibernateUtil;

@SpringBootApplication
public class EpmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpmsApplication.class, args);
		HibernateUtil.getSessionFactory();
		//test commit
	}
	//Test
}
