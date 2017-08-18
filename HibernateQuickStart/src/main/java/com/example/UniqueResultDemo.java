package com.example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

//import javax.persistence.Query;
import com.example.entities.Department;
import com.example.entities.Employee;

//查询检索唯一结果
//当确定返回的实例只有一个或者null时 用uniqueResult()方法
public class UniqueResultDemo {

	public static Department getDepartment(Session session, String deptNo){
		String sql = "Select d from " + Department.class.getName() + " d "
				+ " where d.deptNo = :deptNo ";
		Query query = session.createQuery(sql);
		query.setParameter("deptNo", deptNo);
		return (Department) query.uniqueResult();
	}
	
	public static Employee getEmployee(Session session, Long empId) {
        String sql = "Select e from " + Employee.class.getName() + " e "
                + " where e.empId= :empId ";
        Query query = session.createQuery(sql);
        query.setParameter("empId", empId);
        return (Employee) query.uniqueResult();
    }
	
	public static void main(String[] args){
		
		Configuration conf = new Configuration().configure();	
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        SessionFactory sessionFactory = conf.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();// 从会话工厂获取一个session
        Transaction t = session.beginTransaction();
        
        Department dept = getDepartment(session, "D10");
        Set<Employee> emps = dept.getEmployees();  //private Set<Employee> employees = new HashSet<Employee>(0);

        System.out.println("Dept Name: " + dept.getDeptName());
        for (Employee emp : emps) {
            System.out.println(" Emp name: " + emp.getEmpName());
        }

        Employee emp = getEmployee(session, 7839L);
        System.out.println("Emp Name: " + emp.getEmpName());
        
        t.commit();
        session.close();	
	}	
}
