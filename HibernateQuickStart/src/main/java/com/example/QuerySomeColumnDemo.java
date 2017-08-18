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

//使用HQL语句查询读取多列实例
public class QuerySomeColumnDemo {
	
	public static void main(String[] args){
		
		Configuration conf = new Configuration().configure();	
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        SessionFactory sessionFactory = conf.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();// 从会话工厂获取一个session
        Transaction t = session.beginTransaction();
        
        String sql = "Select e.empId, e.empNo, e.empName from " + Employee.class.getName() + " e ";
        
        //创建List对象Object[] 存储查询数据
        Query query = session.createQuery(sql);
        List<Object[]> datas = query.list();
        
        for(Object[] emp : datas){
        	System.out.println("Emp Id: " + emp[0]);
        	System.out.println("   Emp No: " + emp[1]);
        	System.out.println("   Emp Name: " + emp[2]);
        }
        
        t.commit();
        session.clear();
	}

}
