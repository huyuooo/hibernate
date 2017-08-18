package com.example;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.example.entities.Employee;

//排序查询
public class QueryObjectDemo {
	
	public static void main(String[] args) {
		
		Configuration conf = new Configuration().configure();	
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        SessionFactory sessionFactory = conf.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();// 从会话工厂获取一个session
	       try {

	           // All the action with DB via Hibernate
	           // must be located in one transaction.
	           // Start Transaction.            
	    	   Transaction t = session.beginTransaction();

	           // Create an HQL statement, query the object.
	           // Equivalent to the SQL statement:
	           // Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO
	           String sql = "Select e from " + Employee.class.getName() + " e "
	                   + " order by e.empName, e.empNo ";


	           // Create Query object.
	           Query query = session.createQuery(sql);


	           // Execute query.
	           List<Employee> employees = query.list();

	           for (Employee emp : employees) {
	               System.out.println("Emp: " + emp.getEmpNo() + " : "
	                       + emp.getEmpName());
	           }

	           // Commit data.
	           t.commit();
	           session.close();
	       } catch (Exception e) {
	           e.printStackTrace();
	           // Rollback in case of an error occurred.
	           session.getTransaction().rollback();
	       }
	   }

}
