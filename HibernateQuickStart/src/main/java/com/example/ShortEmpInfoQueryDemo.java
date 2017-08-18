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

//使用HQL和JavaBean查询多列数据
public class ShortEmpInfoQueryDemo {

	public static void main(String[] args){
		
		Configuration conf = new Configuration().configure();	
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        SessionFactory sessionFactory = conf.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();// 从会话工厂获取一个session
        Transaction t = session.beginTransaction();
        
        String sql = "Select new " + ShortEmpInfo.class.getName() + "(e.empId, e.empNo, e.empName)"
        		+ " from " + Employee.class.getName() + " e ";
        
        Query query = session.createQuery(sql);
        List<ShortEmpInfo> employees = query.list();
        
        for (ShortEmpInfo emp : employees) {
			System.out.println("Emp: " + emp.getEmpNo() + ":" +emp.getEmpName());
		}
        
        t.commit();
        session.close();
	}
	
}
