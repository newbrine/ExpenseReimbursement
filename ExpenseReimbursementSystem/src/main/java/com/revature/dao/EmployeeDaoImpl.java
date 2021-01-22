package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.revature.model.Employee;
import com.revature.model.EmployeeType;
import com.revature.utils.ConnectionFactory;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public boolean insertEmployee(Employee emp) {
		boolean result = false;
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "INSERT INTO expense_employee (first_name, last_name, email, username, password, role)"
					+ " VALUES (?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, emp.getFirstName());
			ps.setString(2, emp.getLastName());
			ps.setString(3, emp.getEmail());
			ps.setString(4, emp.getUsername());
			ps.setString(5, emp.getPassword());
			ps.setString(6, emp.getRole().toString());
			
			result = ps.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Employee selectEmployeeById(int id) {
		List<Employee> employees = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * FROM expense_employee WHERE employee_id=? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				employees.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), EmployeeType.valueOf(rs.getString(7))));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return employees.get(0);
	}

	@Override
	public Employee selectEmployeeByEmail(String email) {
		List<Employee> employees = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * FROM expense_employee WHERE email=? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				employees.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), EmployeeType.valueOf(rs.getString(7))));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return employees.get(0);
	}

	@Override
	public Set<Employee> selectAllEmployee() {
		Set<Employee> employees = new HashSet<>();
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * FROM expense_employee";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				employees.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), EmployeeType.valueOf(rs.getString(7))));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
	}

	@Override
	public boolean updateEmployee(Employee emp) {
		boolean result = false;
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "UPDATE expense_employee SET first_name=?, last_name=?, email=?, username=?, password=?, role=?"
					+ "WHERE employee_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, emp.getFirstName());
			ps.setString(2, emp.getLastName());
			ps.setString(3, emp.getEmail());
			ps.setString(4, emp.getUsername());
			ps.setString(5, emp.getPassword());
			ps.setString(6, emp.getRole().toString());
			ps.setInt(7, emp.getEmployeeId());
			
			result = ps.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean deleteEmployee(Employee emp) {
		boolean result = false;
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "DELETE FROM expense_employee WHERE employee_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, emp.getEmployeeId());
			
			result = ps.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean deleteEmployeeById(int id) {
		boolean result = false;
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "DELETE FROM expense_employee WHERE employee_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			result = ps.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
