package com.example;

import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.example.entities.Employee;

public class SaveOrUpdateDetachedDemo {

	public static void main(String[] args){
		
		Employee emp = getEmployee_Detached();
		System.out.println("- Get Emp" + emp.getEmpId());
		// Random delete or not delete Employee
		boolean delete = deleteOrNotDelete(emp.getEmpId());
		
		System.out.println("- Delete?" + delete);
		
		saveOrUpdate_test(emp);
		System.out.println("-EMP ID" + emp.getEmpId());
		
	}

	public static Employee getEmployee_Detached() {

		Configuration conf = new Configuration().configure();
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(conf.getProperties()).build();
		SessionFactory sessionFactory = conf
				.buildSessionFactory(serviceRegistry);

		Session session1 = sessionFactory.openSession();// 从会话工厂获取一个session
		Transaction t1 = session1.beginTransaction();

		Long maxEmpId = DataUtils.getMaxEmpId(session1);
		System.out.println("- Max Emp ID" + maxEmpId);

		Employee emp2 = DataUtils.findEmployee(session1, "E7839");

		Long empId = maxEmpId + 1;
		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpNo("E" + empId);

		emp.setDepartment(emp2.getDepartment());
		emp.setEmpName(emp2.getEmpName());

		emp.setHideDate(emp2.getHideDate());
		emp.setJob("TEST");
		emp.setSalary(1000F);

		session1.persist(emp); // 持久化

		t1.commit();
		session1.close();

		return emp;
	}

	private static boolean deleteOrNotDelete(Long empId) {

		Configuration conf = new Configuration().configure();
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(conf.getProperties()).build();
		SessionFactory sessionFactory = conf
				.buildSessionFactory(serviceRegistry);

		Session session2 = sessionFactory.openSession();// 从会话工厂获取一个session
		Transaction t2 = session2.beginTransaction();

		int random = new Random().nextInt(10);
		if (random < 5) {
			return false;
		}

		String sql = "Delete " + Employee.class.getName() + " e "
				+ " Where e.empId =:empId";
		Query query = session2.createQuery(sql);
		query.setParameter("empId", empId);
		query.executeUpdate();

		t2.commit();
		session2.close();

		return true;
	}

	public static void saveOrUpdate_test(Employee emp) {

		Configuration conf = new Configuration().configure();
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(conf.getProperties()).build();
		SessionFactory sessionFactory = conf
				.buildSessionFactory(serviceRegistry);

		Session session3 = sessionFactory.openSession();// 从会话工厂获取一个session
		Transaction t3 = session3.beginTransaction();

		// Check state of emp
		// ==> false
		System.out.println("- emp Persistent?" + session3.contains(emp));
		System.out.println("- Emp Salary before update:" + emp.getSalary());

		// Set new salary for Detached emp object.
		emp.setSalary(emp.getSalary() + 100);
		// Using saveOrUpdate(emp) to switch emp to Persistent state
        // Note: If exists object same ID in session, this method raise Exception
        //
        // Now, no action with DB. 
		session3.saveOrUpdate(emp);
		
		session3.flush();
		System.out.println(" - Emp salary after update: " + emp.getSalary());
		
		t3.commit();
		session3.close();
	} 
}
