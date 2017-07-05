package com.adp.hoc.epms;

import java.util.Date;

import javax.transaction.Transactional;

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
import com.adp.hoc.epms.entity.MBO;
import com.adp.hoc.epms.entity.MBOCycle;
import com.adp.hoc.epms.entity.MBOType;
import com.adp.hoc.epms.entity.Measurable;
import com.adp.hoc.epms.entity.Organization;
import com.adp.hoc.epms.entity.WeightedMeasurable;
import com.adp.hoc.epms.service.MBOService;
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
			
			Measurable m1 = new Measurable("New Clients", 0, 50);
			WeightedMeasurable wm1 = new WeightedMeasurable();
			wm1.setMeasurable(m1);
			wm1.setWeight(50);
			
			Measurable m2 = new Measurable("Old Clients", 0, 30);
			WeightedMeasurable wm2 = new WeightedMeasurable();
			wm2.setMeasurable(m2);
			wm2.setWeight(25);
			
			Measurable m3 = new Measurable("Country Clients", 0, 30);
			WeightedMeasurable wm3 = new WeightedMeasurable();
			wm3.setMeasurable(m3);
			wm3.setWeight(25);
			
			
			MBO orgMBO = new MBO();
			orgMBO.setDescription("Test MBO");
			orgMBO.setMboType(MBOType.ORGANIZATION_MBO);
			orgMBO.addWeightedMeasurable(wm1);
			orgMBO.addWeightedMeasurable(wm2);
			orgMBO.addWeightedMeasurable(wm3);
			/*orgMBO.setEffectiveFrom(new Date().getTime());
			orgMBO.setEffectiveTill(new Date().getTime());*/
			
			currentSession.save(orgMBO);
			
			m1.setScoreNumerator(25);
			m2.setScoreNumerator(15);
			m3.setScoreNumerator(15);
			
			orgMBO.recalculateScore();
			
			currentSession.update(orgMBO);
			
			WeightedMeasurable derivedOrgMBO = new WeightedMeasurable();
			derivedOrgMBO.setMeasurable(orgMBO);
			derivedOrgMBO.setWeight(25);
			
			Measurable m4 = new Measurable("Some other thing", 0, 25);
			WeightedMeasurable wm4 = new WeightedMeasurable();
			wm4.setMeasurable(m4);
			wm4.setWeight(25);
			
			MBO depMBO = new MBO();
			depMBO.setDescription("Dep MBO");
			depMBO.setMboType(MBOType.DEPARTMENT_MBO);
			depMBO.addWeightedMeasurable(derivedOrgMBO);
			depMBO.addWeightedMeasurable(wm4);
			/*depMBO.setEffectiveFrom(new Date().getTime());
			depMBO.setEffectiveTill(new Date().getTime());*/
			
			currentSession.save(depMBO);
			
			
			
			beginTransaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	public void testMBOCycle(){
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session currentSession = sessionFactory.getCurrentSession();
			Transaction beginTransaction = currentSession.beginTransaction();
			
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
			
			currentSession.save(organization);
			currentSession.save(department);
			currentSession.save(ceoEmployee);
			currentSession.save(managerEmployee);
			currentSession.save(employee5);
			currentSession.save(employee3);
			currentSession.save(employee4);
			
			MBOCycle mboCycle = new MBOCycle(organization, new Date().getTime(), new Date().getTime(), 25, 15);
			currentSession.save(mboCycle);
			
			Measurable m1 = new Measurable("New Clients", 0, 50);
			WeightedMeasurable wm1 = new WeightedMeasurable();
			wm1.setMeasurable(m1);
			wm1.setWeight(50);
			
			Measurable m2 = new Measurable("Old Clients", 0, 30);
			WeightedMeasurable wm2 = new WeightedMeasurable();
			wm2.setMeasurable(m2);
			wm2.setWeight(25);
			
			Measurable m3 = new Measurable("Country Clients", 0, 30);
			WeightedMeasurable wm3 = new WeightedMeasurable();
			wm3.setMeasurable(m3);
			wm3.setWeight(25);
			
			MBO orgMBO = new MBO(MBOType.ORGANIZATION_MBO, mboCycle);
			orgMBO.setDescription("Test MBO");
			orgMBO.addWeightedMeasurable(wm1);
			orgMBO.addWeightedMeasurable(wm2);
			orgMBO.addWeightedMeasurable(wm3);
			currentSession.save(orgMBO);
			
			MBO depMBO = new MBO(MBOType.DEPARTMENT_MBO, mboCycle);
			depMBO.setDescription("Dep MBO");
			
			WeightedMeasurable depWeightedMeasurable = new WeightedMeasurable();
			depWeightedMeasurable.setMeasurable(depMBO);
			depWeightedMeasurable.setWeight(mboCycle.getDepartmentalWeightInEmployeeMBO());
			mboCycle.getDepartmentalWeigtedMeasurableInEmployeeMBO().put(department, depWeightedMeasurable);
			currentSession.save(mboCycle);

			m1.setScoreNumerator(25);
			m2.setScoreNumerator(15);
			m3.setScoreNumerator(15);
			
			orgMBO.recalculateScore();
			
			Measurable m4 = new Measurable("xxxxxxxx", 30, 50);
			WeightedMeasurable wm4 = new WeightedMeasurable();
			wm4.setMeasurable(m4);
			wm4.setWeight(50);
			
			depMBO.addWeightedMeasurable(wm4);
			depMBO.recalculateScore();

			currentSession.save(depMBO);

			currentSession.update(m1);
			currentSession.update(m2);
			currentSession.update(m3);
			
			MBO empMBO = new MBO(MBOType.EMPLOYEE_MBO, mboCycle);
			empMBO.setDescription("Test Emp MBO");
			empMBO.addWeightedMeasurable(mboCycle.getDepartmentalWeigtedMeasurableInEmployeeMBO().get(employee3.getDepartment()));
			
			currentSession.save(empMBO);
			
			beginTransaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
