package com.revature.service;

import java.util.HashSet;
import java.util.Set;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.TicketDao;
import com.revature.dao.TicketDaoImpl;
import com.revature.model.Employee;
import com.revature.model.Ticket;

public class ExpenseManager {
	
	public static TicketDao tDao = new TicketDaoImpl();
	public static EmployeeDao eDao = new EmployeeDaoImpl();
	public static Employee current;

	public static boolean checkCredentials(String email, String password) {
		Set<Employee> employees = eDao.selectAllEmployee();
		boolean result = false;
		
		for(Employee e: employees) {
			if(e.getEmail().equals(email) && e.getPassword().equals(password)){
				current = e;
				result = true;
			}
		}
		return result;
	}
	
	public static Set<Ticket> getWaitingTickets(){
		return tDao.selectPendingTickets();
	}

	public static Set<Ticket> getApprovedTickets(){
		return tDao.selectApprovedTickets();
	}
	
	public static Set<Ticket> getDeniedTickets(){
		return tDao.selectDeniedTickets();
	}
	
	public static boolean denyTicket(Ticket t) {
		return tDao.denyTicket(t);
	}
	
	public static boolean approveTicket(Ticket t) {
		return tDao.approveTicket(t);
	}
	
	public static boolean createTicket(Ticket t) {
		return tDao.insertTicket(t);
	}
	
	public static Set<Ticket> getUserTickets(int id){
		Set<Ticket> userTickets = new HashSet<>();
		
		for(Ticket t: tDao.selectAllTicket()) {
			if(t.getEmployeeId() == id) {
				userTickets.add(t);
			}
		}
		
		return userTickets;
	}
	
	public static Set<Ticket> getAllTickets(){
		Set<Ticket> tickets = new HashSet<>();
		
		for(Ticket t: tDao.selectAllTicket()) {
			tickets.add(t);
		}
		
		return tickets;
	}
	
	public static Employee getEmployeeByEmail(String email) {
		return eDao.selectEmployeeByEmail(email);
	}
	
	public static Ticket getTicketById(int id) {
		return tDao.selectTicketById(id);
	}
	
}
