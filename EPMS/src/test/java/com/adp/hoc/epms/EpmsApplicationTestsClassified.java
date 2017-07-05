package com.adp.hoc.epms;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Rollback;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EpmsApplicationTestsClassified {

	SessionFactory sessionFactory;
	Session currentSession;
	Transaction transaction;
	private long organizationId;
	
	@Before
	public void setUp(){
		sessionFactory = HibernateUtil.getSessionFactory();
		currentSession = sessionFactory.getCurrentSession();
		transaction = currentSession.beginTransaction();
	}
	
	@After
	public void tearDown(){
		transaction.commit();
		currentSession.close();
	}
	
	@Test
	public void test00001_addOrganization(){
		Organization organization = new Organization();
		organization.setOrgName("HOC");
		currentSession.save(organization);
	}
	
	@Test
	public void test00002_addDepartment(){
		Organization organization = currentSession.get(Organization.class, 1L);
		Department department = new Department();
		department.setDepartmentName("GDT");
		department.setOrganization(organization);
		currentSession.save(department);
	}
	
	@Test
	public void test00003_addEmployees(){
		Department department = currentSession.get(Department.class, 1L);
		
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

		currentSession.save(ceoEmployee);
		currentSession.save(managerEmployee);
		currentSession.save(employee4);
		currentSession.save(employee5);
		currentSession.save(employee3);
		currentSession.save(department);
	}
	
	@Test
	public void test00004_addMBOCycle(){
		Organization organization = currentSession.get(Organization.class, 1L);
		
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(2017, Calendar.JULY, 1);
		long from = calendar.getTimeInMillis();
		calendar.add(Calendar.YEAR, 1);
		long to = calendar.getTimeInMillis();
		MBOCycle mboCycle = new MBOCycle(organization, from, to, 25, 15);
		currentSession.save(mboCycle);
	}

	
	@Test
	public void test00005_addOrganizationMBO(){
		Organization organization = currentSession.get(Organization.class, 1L);
		
		CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
		CriteriaQuery<MBOCycle> criteriaQuery = criteriaBuilder.createQuery(MBOCycle.class);
		Root<MBOCycle> root = criteriaQuery.from(MBOCycle.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("organization"), organization));
		MBOCycle mboCycle = currentSession.createQuery(criteriaQuery).getSingleResult();
		
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
		
		organization.getAllMBOsMap().put(mboCycle, orgMBO);
		
		currentSession.save(organization);
	}

	
	@Test
	public void test00006_addDepartmentMBO(){
		Organization organization = currentSession.get(Organization.class, 1L);
		
		CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
		CriteriaQuery<MBOCycle> criteriaQuery = criteriaBuilder.createQuery(MBOCycle.class);
		Root<MBOCycle> root = criteriaQuery.from(MBOCycle.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("organization"), organization));
		MBOCycle mboCycle = currentSession.createQuery(criteriaQuery).getSingleResult();
		
		Department department = currentSession.get(Department.class, 1L);
		
		MBO depMBO = new MBO(MBOType.DEPARTMENT_MBO, mboCycle);
		depMBO.setDescription("Dep MBO");
		WeightedMeasurable depWeightedMeasurable = new WeightedMeasurable();
		depWeightedMeasurable.setMeasurable(depMBO);
		depWeightedMeasurable.setWeight(mboCycle.getDepartmentalWeightInEmployeeMBO());
		mboCycle.getDepartmentalWeigtedMeasurableInEmployeeMBO().put(department, depWeightedMeasurable);
		
		Measurable m1 = new Measurable("New Clients", 0, 100);
		WeightedMeasurable wm1 = new WeightedMeasurable();
		wm1.setMeasurable(m1);
		wm1.setWeight(50);
		
		Measurable m2 = new Measurable("Old Clients", 0, 97);
		WeightedMeasurable wm2 = new WeightedMeasurable();
		wm2.setMeasurable(m2);
		wm2.setWeight(10);
		
		Measurable m3 = new Measurable("Country Clients", 0, 95);
		WeightedMeasurable wm3 = new WeightedMeasurable();
		wm3.setMeasurable(m3);
		wm3.setWeight(15);
		
		depMBO.addWeightedMeasurable(wm1);
		depMBO.addWeightedMeasurable(wm2);
		depMBO.addWeightedMeasurable(wm3);
		
		department.getAllMBOsMap().put(mboCycle, depMBO);
		
		currentSession.save(mboCycle);
		currentSession.save(department);
	}

	
	@Test
	public void test00007_addEmployeeMBO(){
		Organization organization = currentSession.get(Organization.class, 1L);
		
		Employee employee = currentSession.get(Employee.class, 3L);
		
		CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
		CriteriaQuery<MBOCycle> criteriaQuery = criteriaBuilder.createQuery(MBOCycle.class);
		Root<MBOCycle> root = criteriaQuery.from(MBOCycle.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("organization"), organization));
		MBOCycle mboCycle = currentSession.createQuery(criteriaQuery).getSingleResult();
		
		MBO empMBO = new MBO(MBOType.EMPLOYEE_MBO, mboCycle);
		empMBO.setDescription("Test Emp MBO");
		empMBO.addWeightedMeasurable(mboCycle.getDepartmentalWeigtedMeasurableInEmployeeMBO().get(employee.getDepartment()));
		
		Measurable m1 = new Measurable("New Clients", 0, 15);
		WeightedMeasurable wm1 = new WeightedMeasurable();
		wm1.setMeasurable(m1);
		wm1.setWeight(50);
		
		Measurable m2 = new Measurable("Old Clients", 0, 15);
		WeightedMeasurable wm2 = new WeightedMeasurable();
		wm2.setMeasurable(m2);
		wm2.setWeight(15);
		
		Measurable m3 = new Measurable("Country Clients", 0, 30);
		WeightedMeasurable wm3 = new WeightedMeasurable();
		wm3.setMeasurable(m3);
		wm3.setWeight(20);
		
		empMBO.addWeightedMeasurable(wm1);
		empMBO.addWeightedMeasurable(wm2);
		empMBO.addWeightedMeasurable(wm3);
		
		employee.getAllMBOsMap().put(mboCycle, empMBO);
		
		//currentSession.save(empMBO);
		currentSession.save(employee);
		
	}
	
	@Test
	public void test00008_updateEmployeeMBO(){
		Employee employee = currentSession.get(Employee.class, 3L);
		Organization organization = employee.getDepartment().getOrganization();
		
		CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
		CriteriaQuery<MBOCycle> criteriaQuery = criteriaBuilder.createQuery(MBOCycle.class);
		Root<MBOCycle> root = criteriaQuery.from(MBOCycle.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("organization"), organization));
		MBOCycle mboCycle = currentSession.createQuery(criteriaQuery).getSingleResult();
		
		MBO mbo = employee.getAllMBOsMap().get(mboCycle);
		
		if(mbo != null){
			for (WeightedMeasurable wm : mbo.getWeightedMeasurables()) {
				Measurable measurable = wm.getMeasurable();
				if(!(measurable instanceof MBO)){
					measurable.setScoreNumerator(measurable.getScoreDenominator()/2);
				} else{
					System.out.println("Excluding MBO");
				}
			}
		}
		mbo.recalculateScore();
		currentSession.update(employee);
	}
	
	@Test
	public void test00009_updateDepartmentMBO(){
		Department department = currentSession.get(Department.class, 1L);
		Organization organization = department.getOrganization();
		
		CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
		CriteriaQuery<MBOCycle> criteriaQuery = criteriaBuilder.createQuery(MBOCycle.class);
		Root<MBOCycle> root = criteriaQuery.from(MBOCycle.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("organization"), organization));
		MBOCycle mboCycle = currentSession.createQuery(criteriaQuery).getSingleResult();
		
		MBO mbo = department.getAllMBOsMap().get(mboCycle);
		
		if(mbo != null){
			for (WeightedMeasurable wm : mbo.getWeightedMeasurables()) {
				Measurable measurable = wm.getMeasurable();
				if(!(measurable instanceof MBO)){
					measurable.setScoreNumerator(measurable.getScoreDenominator()/2);
				} else{
					System.out.println("Excluding MBO");
				}
			}
		}
		mbo.recalculateScore();
		
		CriteriaQuery<Employee> empCriteria = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> from = empCriteria.from(Employee.class);
		empCriteria.select(from).where(criteriaBuilder.equal(from.get("department"), department));
		Query<Employee> empQuery = currentSession.createQuery(empCriteria);
		List<Employee> employeeList = empQuery.list();
		
		for (Employee employee : employeeList) {
			MBO empMBO = employee.getAllMBOsMap().get(mboCycle);
			if (empMBO != null) {
				empMBO.recalculateScore();
				currentSession.update(employee);
			}
		}
		
		currentSession.update(department);
	}
	
	@Test
	public void test00010_updateOrganizationalMBO(){
		Department department = currentSession.get(Department.class, 1L);
		Organization organization = department.getOrganization();
		
		CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
		CriteriaQuery<MBOCycle> criteriaQuery = criteriaBuilder.createQuery(MBOCycle.class);
		Root<MBOCycle> root = criteriaQuery.from(MBOCycle.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("organization"), organization));
		MBOCycle mboCycle = currentSession.createQuery(criteriaQuery).getSingleResult();
		
		//MBO mbo = department.getAllMBOsMap().get(mboCycle);
		MBO mbo = organization.getAllMBOsMap().get(mboCycle);
		
		if(mbo != null){
			for (WeightedMeasurable wm : mbo.getWeightedMeasurables()) {
				Measurable measurable = wm.getMeasurable();
				if(!(measurable instanceof MBO)){
					measurable.setScoreNumerator(measurable.getScoreDenominator()/2);
				} else{
					System.out.println("Excluding MBO");
				}
			}
			mbo.recalculateScore();
		}
		
		CriteriaQuery<Department> depQuery = criteriaBuilder.createQuery(Department.class);
		Root<Department> depRoot = depQuery.from(Department.class);
		depQuery.select(depRoot).where(criteriaBuilder.equal(depRoot.get("organization"), organization));
		List<Department> departments = currentSession.createQuery(depQuery).list();
		
		for (Department dep : departments) {
			MBO depMBO = dep.getAllMBOsMap().get(mboCycle);
			depMBO.recalculateScore();
			currentSession.update(depMBO);
		}
		
		CriteriaQuery<Employee> empCriteria = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> from = empCriteria.from(Employee.class);
		Query<Employee> empQuery = currentSession.createQuery(empCriteria);
		List<Employee> employeeList = empQuery.list();
		
		for (Employee employee : employeeList) {
			MBO empMBO = employee.getAllMBOsMap().get(mboCycle);
			if (empMBO != null) {
				empMBO.recalculateScore();
				currentSession.update(employee);
			}
		}
		
		currentSession.update(department);
	}

}
