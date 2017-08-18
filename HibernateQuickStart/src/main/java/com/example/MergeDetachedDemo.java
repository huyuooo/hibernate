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

//分离转变为持久性状态：使用merge(Object)
public class MergeDetachedDemo {

	public static void main(String[] args) {

		// An object has Detached status
		Employee emp = getEmployee_Detached();

		System.out.println(" - GET EMP " + emp.getEmpId());

		// Random: delete or not delete the Employee by ID.
		boolean delete = deleteOrNotDelete(emp.getEmpId());

		System.out.println(" - DELETE? " + delete);

		// Call saveOrUpdate Detached object
		saveOrUpdate_test(emp);

		// After call saveOrUpdate
		// ...
		System.out.println(" - EMP ID " + emp.getEmpId());
	}

	// Method return Employee object
	// and has Detached status.
	private static Employee getEmployee_Detached() {
		
		Configuration conf = new Configuration().configure();
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(conf.getProperties()).build();
		SessionFactory sessionFactory = conf
				.buildSessionFactory(serviceRegistry);

		Session session1 = sessionFactory.openSession();// 从会话工厂获取一个session
		Transaction t1 = session1.beginTransaction();
		Employee emp = null;
		
		Long maxEmpId = DataUtils.getMaxEmpId(session1);
        System.out.println(" - Max Emp ID " + maxEmpId);

        Employee emp2 = DataUtils.findEmployee(session1, "E7839");

        Long empId = maxEmpId + 1;
        emp = new Employee();
        emp.setEmpId(empId);
        emp.setEmpNo("E" + empId);

        emp.setDepartment(emp2.getDepartment());
        emp.setEmpName(emp2.getEmpName());

        emp.setHideDate(emp2.getHideDate());
        emp.setJob("Test");
        emp.setSalary(1000F);

        // 'emp' has Persistant state
        session1.persist(emp);
        
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
	
	private static void saveOrUpdate_test(Employee emp) {
		
		Configuration conf = new Configuration().configure();
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(conf.getProperties()).build();
		SessionFactory sessionFactory = conf.buildSessionFactory(serviceRegistry);
		
		Session session3 = sessionFactory.openSession();// 从会话工厂获取一个session
		Transaction t3 = session3.beginTransaction();
		
		System.out.println(" - emp Persistent? " + session3.contains(emp));

        System.out.println(" - Emp salary before update: " + emp.getSalary());

        // Set new salary for Detached object 'emp'
        emp.setSalary(emp.getSalary() + 100);

        // merge(emp) return empMerge, a copy of 'emp',
        // empMerge managed by Hibernate
        // 'emp' still in Detached state
        //
        // At this time there is no action regarding DB.
        Employee empMerge = (Employee) session3.merge(emp);

        // ==> false
        System.out.println(" - emp Persistent? " + session3.contains(emp));
        // ==> true
        System.out.println(" - empMerge Persistent? "
                + session3.contains(empMerge));


        // Push data into the DB.
        // Here it is possible to create the Insert or Update on DB.
        // If the corresponding record has been deleted by someone, it insert
        // else it update
        session3.flush();

        System.out.println(" - Emp salary after update: " + emp.getSalary());
        
        t3.commit();
        session3.close();		
	}
}
