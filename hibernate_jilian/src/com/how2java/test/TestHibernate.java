package com.how2java.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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
        
        /*
         *delete()级联  在一对多中应用 删除“一”时，对应“多”中关联的也被删除 
        Category c = (Category) s.get(Category.class, 3);
        s.delete(c);
        */
        Category c = (Category) s.get(Category.class, 5);
        
        Product p1 = new Product();  // 一
        p1.setName("product_501");
        Product p2 = new Product();
        p2.setName("product_502");
        Product p3 = new Product();
        p3.setName("product_503");   
 
        c.getProducts().add(p1);  //多     多的一方更新提交了   一的一方也跟着更新提交
        c.getProducts().add(p2);
        c.getProducts().add(p3);
        	
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
	
	public static void main4delete(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
          Session s = sf.openSession();
          s.beginTransaction();
          Category c = (Category) s.get(Category.class, 3);
          s.delete(c);
          s.getTransaction().commit();
          s.close();
          sf.close();
	}

}
