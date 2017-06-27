package com.adp.hoc.epms;

import java.util.Date;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.adp.hoc.epms.entity.Department;
import com.adp.hoc.epms.entity.Employee;
import com.adp.hoc.epms.entity.JobLevel;
import com.adp.hoc.epms.entity.Organization;
import com.adp.hoc.epms.utility.HibernateUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EpmsApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	@Transactional
	public void addOrgDepEmp(){
		try {
			Organization organization = new Organization();
			organization.setLastMBORefreshDate(new Date().getTime());
			organization.setOrgName("HOC");
			
			Department department = new Department();
			department.setDepartmentName("GDT");
			department.setOrganization(organization);
			
			Employee employee1 = new Employee("A","B","C",JobLevel.CEO, "CEO", new Date().getTime());
			employee1.setDepartment(department);
			
			Employee employee2 = new Employee("A","B","C",JobLevel.HOD, "Manager", new Date().getTime());
			employee2.setDepartment(department);
			employee2.setManager(employee1);

			Employee employee3 = new Employee("A","B","C",JobLevel.Employee, "Employee", new Date().getTime());
			employee3.setDepartment(department);
			employee3.setManager(employee2);

			Employee employee4 = new Employee("A","B","C",JobLevel.Employee, "Employee", new Date().getTime());
			employee4.setDepartment(department);
			employee4.setManager(employee2);

			Employee employee5 = new Employee("A","B","C",JobLevel.Employee, "Employee", new Date().getTime());
			employee5.setDepartment(department);
			employee5.setManager(employee2);
			
			department.setHeadOfDepartment(employee2);

			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session currentSession = sessionFactory.getCurrentSession();
			Transaction beginTransaction = currentSession.beginTransaction();
			
			currentSession.save(organization);
			currentSession.save(department);
			currentSession.save(employee1);
			currentSession.save(employee2);
			currentSession.save(employee5);
			currentSession.save(employee3);
			currentSession.save(employee4);
			
			beginTransaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
