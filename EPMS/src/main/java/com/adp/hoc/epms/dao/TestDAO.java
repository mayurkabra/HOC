package com.adp.hoc.epms.dao;

import java.util.Date;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.adp.hoc.epms.entity.Organization;
import com.adp.hoc.epms.utility.HibernateUtil;

@Repository
public class TestDAO {

	@Transactional
	public void addOrganizationTest(String companyName, Date date){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			/*Organization organization = new Organization();
			organization.setLastMBORefreshDate(date.getTime());
			organization.setOrgName(companyName);
			session.save(organization);*/
			
			Query query = session.createNativeQuery("INSERT INTO [dbo].[Organization] ([orgName] ,[lastMBORefreshDate]) "
					+ "VALUES (:orgName ,:lastMBORefreshDate)");
			query.setParameter("orgName", "ADP");
			query.setParameter("lastMBORefreshDate", new Date().getTime());
			
			query.executeUpdate();
			
			/*VehicleArrival arrival = new VehicleArrival(0,"a","a","a",new Date(), 12,1,1);
			session.save(arrival);*/
			
			/*Request request = new Request("a", "b", new Date(), "afssa", new Date());
			session.save(request);*/
			
			tx.commit();
		}
		catch (RuntimeException e) {
			if(tx != null){
				tx.rollback();
			}
			throw e;
		}
		finally {
			session.close();
		}
	}

}
