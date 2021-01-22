package com.revature.dao;

import java.util.Set;

import com.revature.model.Employee;

public interface EmployeeDao {

	
	//CRUD
	
	//Create
	public boolean insertEmployee(Employee e);
	
	//Receive
	public Employee selectEmployeeById(int id);
	public Employee selectEmployeeByEmail(String email);
	public Set<Employee> selectAllEmployee();
	
	//Update
	public boolean updateEmployee(Employee e);
	
	//Delete
	public boolean deleteEmployee(Employee e);
	public boolean deleteEmployeeById(int id);
}
