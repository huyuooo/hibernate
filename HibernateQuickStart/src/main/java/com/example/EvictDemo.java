package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.example.entities.Employee;

public class EvictDemo {
	
	public static void main(String[] args){
		
		Configuration conf = new Configuration().configure();	
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        SessionFactory sessionFactory = conf.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();// 从会话工厂获取一个session
        Transaction t = session.beginTransaction();
	
        Employee emp = null;
        emp = DataUtils.findEmployee(session, "E7499");
        System.out.println("- emp Persisent?" + session.contains(emp));
        
        session.evict(emp);
        System.out.println("- emp Persisent?" + session.contains(emp));
        
        emp.setEmpNo("NEW");
        
        t.commit();
        session.close();        
	}

}
