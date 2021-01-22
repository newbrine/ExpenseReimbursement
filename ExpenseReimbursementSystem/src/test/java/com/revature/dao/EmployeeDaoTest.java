package com.revature.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.model.Employee;
import com.revature.model.EmployeeType;

public class EmployeeDaoTest {

	@Test
	public void testSelectById() {
		Employee test = new Employee(1,"Ryan", "Murray", "newbrine@gmail.com", "newbrine", "password", EmployeeType.MANAGER);
		Employee data = new EmployeeDaoImpl().selectEmployeeById(1);
		assertEquals(test, data);		
	}
	
//	@Test
//	public void testInsert() {
//		Employee test = new Employee(8, "Tim", "Stuetzle", "timmy@gmail.com", "tstuz", "18numba1", EmployeeType.EMPLOYEE);
//		new EmployeeDaoImpl().insertEmployee(test);
//		assertEquals(test, new EmployeeDaoImpl().selectEmployeeByUsername("tstuz"));
//	}
	
//	@Test
//	public void testDelete() {
//		Employee test = new Employee(10, "Tim", "Stuetzle", "timmy@gmail.com", "tstuz", "18numba1", EmployeeType.EMPLOYEE);
//		assertTrue(new EmployeeDaoImpl().deleteEmployee(test));
//	}

}
