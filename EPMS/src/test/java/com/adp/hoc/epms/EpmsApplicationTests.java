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
			
			Employee ceoEmployee = new Employee("A","B","C",JobLevel.CEO, "CEO", new Date().getTime());
			ceoEmployee.setDepartment(department);
			
			Employee managerEmployee = new Employee("A","B","C",JobLevel.HOD, "Manager", new Date().getTime());
			managerEmployee.setDepartment(department);
			managerEmployee.setManager(ceoEmployee);

			Employee employee3 = new Employee("A","B","C",JobLevel.Employee, "Employee", new Date().getTime());
			employee3.setDepartment(department);
			employee3.setManager(managerEmployee);

			Employee employee4 = new Employee("A","B","C",JobLevel.Employee, "Employee", new Date().getTime());
			employee4.setDepartment(department);
			employee4.setManager(managerEmployee);

			Employee employee5 = new Employee("A","B","C",JobLevel.Employee, "Employee", new Date().getTime());
			employee5.setDepartment(department);
			employee5.setManager(managerEmployee);
			employee5.setActive(false);
			
			department.setHeadOfDepartment(managerEmployee);

			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session currentSession = sessionFactory.getCurrentSession();
			Transaction beginTransaction = currentSession.beginTransaction();
			
			currentSession.save(organization);
			currentSession.save(department);
			currentSession.save(ceoEmployee);
			currentSession.save(managerEmployee);
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
