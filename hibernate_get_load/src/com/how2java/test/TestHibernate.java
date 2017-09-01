package com.how2java.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.event.spi.SaveOrUpdateEvent;

import com.how2java.pojo.Category;
import com.how2java.pojo.Product;
import com.how2java.pojo.User;
import com.sun.corba.se.impl.interceptors.PICurrent;

public class TestHibernate {
	public static void main(String[] args) {
		 
        SessionFactory sf = new Configuration().configure().buildSessionFactory(); 
        Session s = sf.openSession();
        s.beginTransaction();
              
        //sql语句访问先后不同
        System.out.println("log1");
        Product p = (Product) s.get(Product.class, 1);
        System.out.println("log2");
        Product p2 = (Product) s.load(Product.class, 2);
        System.out.println("log3");
        System.out.println(p2.getName());
        System.out.println("log4");

        //当查询id不存在时  get方式返回空  load方式会抛出异常
        Product p3 = (Product)s.get(Product.class, 500);
        System.out.println("p3="+p3);
         
        Product p4 = (Product)s.load(Product.class, 500);
        System.out.println("p3="+p4);
        
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
