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
        
        //分页查询
        String name = "iphone";
        
        Criteria c = s.createCriteria(Product.class);
        c.add(Restrictions.like("name", "%"+name+"%"));
        c.setFirstResult(2); //从第三条开始记录  从0开始计数
        c.setMaxResults(5);  //取出五条
        
        List<Product> ps = c.list();
        for (Product p : ps) {
			System.out.println(p.getName());
		}
        
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
