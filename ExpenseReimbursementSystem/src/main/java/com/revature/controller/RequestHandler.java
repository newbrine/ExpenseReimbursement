package com.revature.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class RequestHandler {
	
	final static Logger log = Logger.getLogger(RequestHandler.class);

	public static void process(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		
		String endpoint = req.getRequestURI();
		log.debug(new Timestamp(System.currentTimeMillis()) + ": " + endpoint + " has been accessed.");
		
		switch(endpoint) {
		case "/ExpenseReimbursementSystem/expense/home":
			HomeController.homePage(req, resp);
			break;
		case "/ExpenseReimbursementSystem/expense/EmpLogin":
			LoginController.getEmployeeLoginPage(req,resp);
			break;
		case "/ExpenseReimbursementSystem/expense/check":
			LoginController.setupCredentials(req,resp);
			break;
		case "/ExpenseReimbursementSystem/expense/logout":
			LoginController.logout(req,resp);
			break;
		case "/ExpenseReimbursementSystem/expense/EmpMenu":
			LoginController.displayEmployeeMenu(req,resp);
			break;
		case "/ExpenseReimbursementSystem/expense/empCreateTicket":
			EmployeeController.createTicket(req, resp);
			break;
		case "/ExpenseReimbursementSystem/expense/empViewPast":
			EmployeeController.viewPastTickets(req,resp);
			break;
		case "/ExpenseReimbursementSystem/expense/manViewPast":
			ManagerController.viewPastTickets(req,resp);
			break;
		case "/ExpenseReimbursementSystem/expense/manCreateTicket":
			ManagerController.createTicket(req,resp);
			break;
		case "/ExpenseReimbursementSystem/expense/MangMenu":
			LoginController.displayManagerMenu(req,resp);
			break;
		case "/ExpenseReimbursementSystem/expense/approve":
			ManagerController.approveTicket(req, resp);
			break;
		case "/ExpenseReimbursementSystem/expense/deny":
			ManagerController.denyTicket(req, resp);
			break;
		}
	}
	
}
