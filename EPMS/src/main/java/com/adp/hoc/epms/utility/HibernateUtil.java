package com.adp.hoc.epms.utility;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.adp.hoc.epms.entity.Department;
import com.adp.hoc.epms.entity.Employee;
import com.adp.hoc.epms.entity.JobLevel;
import com.adp.hoc.epms.entity.Organization;

public class HibernateUtil {
	  private static StandardServiceRegistry registry;
	  private static SessionFactory sessionFactory;

	  public static SessionFactory getSessionFactory() {
	    if (sessionFactory == null) {
	      try {

	        // Create registry builder
	        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

	        // Hibernate settings equivalent to hibernate.cfg.xml's properties
	        Map<String, String> settings = new HashMap<>();
	        settings.put("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        settings.put("hibernate.connection.url", "jdbc:sqlserver://localhost:1433;databaseName=EPMSv1;integratedSecurity=false;");
	        settings.put("hibernate.connection.username", "hocadmin");
	        settings.put("hibernate.connection.password", "Pass@123");
	        settings.put("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
	        settings.put("hibernate.show_sql", "true");
	        settings.put("hibernate.hbm2ddl.auto", "create");
	        settings.put("hibernate.current_session_context_class", "thread");

	        // Apply settings
	        registryBuilder.applySettings(settings);

	        // Create registry
	        registry = registryBuilder.build();

	        // Create MetadataSources
	        MetadataSources sources = new MetadataSources(registry);
	        
	        //Add annotated classes here
	        sources.addAnnotatedClass(Organization.class);
	        sources.addAnnotatedClass(Employee.class);
	        sources.addAnnotatedClass(Department.class);
	        /*sources.addAnnotatedClass(JobLevel.class);*/

	        // Create Metadata
	        Metadata metadata = sources.getMetadataBuilder().build();

	        // Create SessionFactory
	        sessionFactory = metadata.getSessionFactoryBuilder().build();

	      } catch (Exception e) {
	        e.printStackTrace();
	        if (registry != null) {
	          StandardServiceRegistryBuilder.destroy(registry);
	        }
	      }
	    }
	    return sessionFactory;
	  }

	  public static void shutdown() {
	    if (registry != null) {
	      StandardServiceRegistryBuilder.destroy(registry);
	    }
	  }
	}