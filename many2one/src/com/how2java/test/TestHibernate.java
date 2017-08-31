package com.how2java.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.how2java.pojo.Category;
import com.how2java.pojo.Product;
import com.how2java.pojo.User;

public class TestHibernate {
	public static void main(String[] args) {
		 
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
 
        Session s = sf.openSession();
        s.beginTransaction();

        /*  多对一
        Category c =new Category();
        c.setName("c5");
//        c.setId(7);   自增主键自己赋值无效
        s.save(c);
         
        Product p = (Product) s.get(Product.class, 3);
        p.setCategory(c);
        s.update(p);
        */
        
        /* 多对多
        Category c = (Category) s.get(Category.class, 1);
        Set<Product> ps = c.getProducts();
        for (Product p : ps) {
			System.out.println(p.getName());
		}
		*/
        
      //增加3个用户
        Set<User> users = new HashSet();
        for (int i = 0; i < 3; i++) {
            User u =new User();
            u.setName("user"+i);
            users.add(u);
            s.save(u);
        }
          
        //产品1被用户1,2,3购买
        Product p1 = (Product) s.get(Product.class, 1);
          
        p1.setUsers(users);
        s.save(p1);
        
        //产品1,2,3被用户1购买
//        Product p2 = (Product) s.get(Product.class, 2);
//        Product p3 = (Product) s.get(Product.class, 3);
        
//        User user1 = (User) s.get(User.class, 1);
//        user1.getProducts().add(p1);
//        user1.getProducts().add(p2);
//        user1.getProducts().add(p3);
 //       s.save(user1);
               
        s.getTransaction().commit();
        s.close();
        sf.close();
    }

}
