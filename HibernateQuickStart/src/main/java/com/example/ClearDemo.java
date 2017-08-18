package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.example.entities.Department;
import com.example.entities.Employee;

public class ClearDemo {
	
	public static void main(String[] args){
		
		Configuration conf = new Configuration().configure();	
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        SessionFactory sessionFactory = conf.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();// 从会话工厂获取一个session
        Transaction t = session.beginTransaction();
        
        Employee emp = null;
        Department dept = null;
        
        emp = DataUtils.findEmployee(session, "E7499");
        dept = DataUtils.findDepartment(session, "D10");
        
        session.clear();
        
        System.out.println("- emp Persistent?" + session.contains(emp));
        System.out.println("- dept Persistent?" + session.contains(dept));
        
        // All change on the 'emp' will not update
        // if not reatach 'emp' to session
        emp.setEmpNo("NEW");

        dept = DataUtils.findDepartment(session, "D20");
        System.out.println("Dept Name = "+ dept.getDeptName());

        session.getTransaction().commit();
	}

}
