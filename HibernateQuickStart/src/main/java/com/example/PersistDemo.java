package com.example;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.example.entities.Department;
import com.example.entities.Employee;

public class PersistDemo {
	
	public static void main(String[] args){
		
		Configuration conf = new Configuration().configure();	
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        SessionFactory sessionFactory = conf.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();// 从会话工厂获取一个session
        Transaction t = session.beginTransaction();
        
        Department department = null;
        Employee employee = null;
        
        Long maxEmpId = DataUtils.getMaxEmpId(session); //获取最大ID号
        Long empId = maxEmpId + 1;        //定义ID 为 最大值ID + 1
        department = DataUtils.findDepartment(session, "D10");
        
        Employee emp = new Employee();
        emp.setEmpId(empId);
        emp.setEmpNo("E" + empId);
        emp.setEmpName("Name" + empId);
        emp.setJob("Coder");
        emp.setSalary(1000f);
        emp.setManager(null);
        emp.setHideDate(new Date());
        emp.setDepartment(department);
        
        //持久化
        session.persist(emp);
        
        t.commit();
        session.close(); 
        
        System.out.println("Emp No: " + emp.getEmpNo());
	}

}
