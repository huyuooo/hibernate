package com.example;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.example.entities.Employee;

//条件查询
public class QueryObjectDemo2 {
	
	public static void main(String[] args) {
		
		Configuration conf = new Configuration().configure();	
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        SessionFactory sessionFactory = conf.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();// 从会话工厂获取一个session
        Transaction t = session.beginTransaction();
        
        String sql = "Select e from " + Employee.class.getName() + " e " + " where e.department.deptNo=:deptNo ";
        
        Query query = session.createQuery(sql);

        query.setParameter("deptNo", "D10");   //查询条件：deptNo 为 D10

        // Execute query.
        List<Employee> employees = query.list();

        for (Employee emp : employees) {
            System.out.println("Emp: " + emp.getEmpNo() + " : " + emp.getEmpName());
        }
        
        t.commit();
        session.close();
	}

}
