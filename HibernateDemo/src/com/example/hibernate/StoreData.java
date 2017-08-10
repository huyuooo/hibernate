package com.example.hibernate;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;


public class StoreData {
	
	public static void main(String[] args) {  

		Configuration conf = new Configuration().configure();
	    
        SessionFactory sessionFactory = conf.buildSessionFactory(
        		new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build());
        Session sess = sessionFactory.openSession();// 从会话工厂获取一个session
        Transaction t = sess.beginTransaction();

        Employee e1 = new Employee();
        e1.setId(1003);
        e1.setFirstName("Yii");
        e1.setLastName("Bai");

        Employee e2 = new Employee();
        e2.setId(1004);
        e2.setFirstName("Min");
        e2.setLastName("Su");

        sess.persist(e1);
        sess.persist(e2);

        t.commit();
        sess.close();
        System.out.println("successfully saved");
    }

}
