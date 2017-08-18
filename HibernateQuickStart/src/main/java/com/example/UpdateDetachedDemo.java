package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.example.entities.Employee;

//分离转变为持久性状态：使用update(Object)
public class UpdateDetachedDemo {
	
	public static void main(String[] args){
		
		Configuration conf = new Configuration().configure();	
	    StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
	    SessionFactory sessionFactory = conf.buildSessionFactory(serviceRegistry);
	    
	    Session session1 = sessionFactory.openSession();// 从会话工厂获取一个session
	    Transaction t1 = session1.beginTransaction();
	    
	    Employee emp = null;
	    emp = DataUtils.findEmployee(session1, "E7499");
	    System.out.println("- emp Persistent? " + session1.contains(emp));
	    
	    t1.commit();
	    session1.close();
	    
	    Session session2 = sessionFactory.openSession();// 从会话工厂获取一个session
	    Transaction t2 = session2.beginTransaction();	    
	    
	    System.out.println("- emp Persistent? " + session2.contains(emp));
	    
	    System.out.println("Emp salary: " + emp.getSalary());
        emp.setSalary(emp.getSalary() + 100);
        
        // update (..) is only used for Detached object.
        // (Not for Transient object).
        // Use the update (emp) to bring back emp Persistent state.
        // update 将分离带带回到持久化状态
        session2.update(emp);
        
        // Call flush
        // Update statement will be called.
        session2.flush();

        System.out.println("Emp salary after update: " + emp.getSalary());
        t2.commit();
        session2.close();
				
	}	   
}
