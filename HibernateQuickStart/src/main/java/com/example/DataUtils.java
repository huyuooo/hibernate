package com.example;

import org.hibernate.Query;
import org.hibernate.Session;
import com.example.entities.Department;
import com.example.entities.Employee;

//使用Session.persist(Object)将瞬态对象插入数据库的简单示例
public class DataUtils {

	public static Department findDepartment(Session session, String deptNo){
		String sql = "Select d from " + Department.class.getName() + " d "
				+ " Where d.deptNo = :deptNo";
		Query query = session.createQuery(sql);
		query.setParameter("deptNo", deptNo);
		return (Department) query.uniqueResult();
	}
	
	public static Long getMaxEmpId(Session session){
		String sql = "Select max(e.empId) from " + Employee.class.getName() + " e ";
		Query query = session.createQuery(sql);
		Number value = (Number) query.uniqueResult();
		if (value == null) {
			return 0L;
		}
		return value.longValue();
	}
	
	public static Employee findEmployee(Session session, String empNo){
		String sql = "Select e from " + Employee.class.getName() + " e " 
				+ " Where e.empNo = :empNo ";
		Query query = session.createQuery(sql);
		query.setParameter("empNo", empNo);
		return (Employee) query.uniqueResult();
	}	
}
