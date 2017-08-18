package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.example.entities.Department;

//持久化(Persistent)
//当一个对像使用 Session 的get(),load(),find()方法获取关联数据时，它处于持久化(Persistent)状态。
public class PersistentDemo {
	
	public static void main(String[] args){
		
		Configuration conf = new Configuration().configure();	
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        SessionFactory sessionFactory = conf.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();// 从会话工厂获取一个session
        Transaction t = session.beginTransaction();
        
        Department department = null;
        
        // Persistent object.
        System.out.println("- Finding Department deptNo = D10...");
        department = DataUtils.findDepartment(session, "D10");
        System.out.println("- First change Location");
        
        // Changing something on Persistent object.
        department.setLocation("Chicago" + System.currentTimeMillis());
        System.out.println("-Location = " + department.getLocation());
        System.out.println("-Calling flush...");
        
        // Use session.flush () to actively push the changes to the DB.
        // It works for all changed Persistent objects.
        session.flush();
        
        System.out.println("- Flush OK");
        System.out.println("-Sencond change Location");
        department.setLocation("Chicago " + System.currentTimeMillis());
        System.out.println("- Location = " + department.getLocation());
        System.out.println("- Calling commit...");
        
        t.commit();
        session.close();
        
        System.out.println("- Commit OK");
        
		
	}

}
