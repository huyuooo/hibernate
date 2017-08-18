package com.example;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.example.entities.Employee;
import com.example.entities.Timekeeper;

//瞬态转为持久化状态：使用save(Object)
public class SaveTransientDemo {
	
	private static DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private static Timekeeper persist_Transient(Session session, Employee emp){
		
		// See configuration of timekeeperId:
		// @GeneratedValue(generator = "uuid")
		// @GenericGenerator(name = "uuid", strategy = "uuid2")
		// Create an Object, Transitent state.
		Timekeeper tk2 = new Timekeeper();

		tk2.setEmployee(emp);
		tk2.setInOut(Timekeeper.IN);
		tk2.setDateTime(new Date());

		System.out.println("- tk2 Persistent? " + session.contains(tk2));

		System.out.println("====== CALL save(tk).... ===========");

		// save() very similar to persist()
		// save() return ID, persist() return void.
		// Hibernate assign ID value to 'tk2', no action with DB
		// And return ID of 'tk2'.    
//		Serializable id = session.save(tk2);
		//session.saveOrUpdate(tk2);
		session.merge(tk2);
		
//		System.out.println("- id = " + id);
		System.out.println("- tk2.getTimekeeperId() = " + tk2.getTimekeeperId());

		// Now, 'tk2' has Persistent state
		// It has been managed in Session.
		// ==> true
		System.out.println("- tk2 Persistent? " + session.contains(tk2));

		System.out.println("- Call flush..");

		// To push data into the DB, call flush().
		// If not call flush() data will be pushed to the DB when calling
		// commit().
		// Will execute insert statement.
		session.flush();

		String timekeeperId = tk2.getTimekeeperId();
		System.out.println("- timekeeperId = " + timekeeperId);
		System.out.println("- inOut = " + tk2.getInOut());
		System.out.println("- dateTime = " + df.format(tk2.getDateTime()));
		System.out.println();
		return tk2;
	}
	
	public static void main(String[] args) {

		Configuration conf = new Configuration().configure();
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(conf.getProperties()).build();
		SessionFactory sessionFactory = conf
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();// 从会话工厂获取一个session
		Transaction t = session.beginTransaction();

		Employee emp = null;
		emp = DataUtils.findEmployee(session, "E7499");
		persist_Transient(session, emp);

		t.commit();
		session.close();
	}

}
