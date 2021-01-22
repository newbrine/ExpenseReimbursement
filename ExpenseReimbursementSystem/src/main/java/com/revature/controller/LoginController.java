package com.revature.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.model.EmployeeType;
import com.revature.service.ExpenseManager;

public class LoginController {
	
	final static Logger log = Logger.getLogger(LoginController.class);

	public static void getEmployeeLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher redis = req.getRequestDispatcher("/employeeLogin.html");
		
		redis.forward(req, resp);
	}

	public static void setupCredentials(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sesh = req.getSession();
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		System.out.println(email);
		System.out.println(password);
		
		sesh.setAttribute("email", email);
		sesh.setAttribute("password", password);
		
		checkCredentials(req,resp);
	}
	
	public static void checkCredentials(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ses = req.getSession(false);
		
		if(ses!=null) {
			if(req.getMethod().equals("POST")) {
				if(ExpenseManager.checkCredentials(ses.getAttribute("email").toString(), ses.getAttribute("password").toString())) {
					Employee e = ExpenseManager.getEmployeeByEmail((String) ses.getAttribute("email"));
					ses.setAttribute("id", e.getEmployeeId());
					ses.setAttribute("Employee", e);
					ses.setAttribute("Access", true);
					
					log.debug(new Timestamp(System.currentTimeMillis()) + ": Employee " + e.getEmployeeId() + " has logged in.");
					
					if(e.getRole().equals(EmployeeType.MANAGER)) {
						resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/expense/MangMenu");
					} else {
						resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/expense/EmpMenu");
					}
					
					resp.setStatus(200);
				} else {
					resp.setStatus(403);
				}
			} else {
				resp.setStatus(405);
			}
		}

	}

	public static void displayEmployeeMenu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ses = req.getSession(false);
		
		if(ses==null) {
			RequestDispatcher redis = req.getRequestDispatcher("/index.html");
			
			redis.forward(req, resp);
		} else {
			RequestDispatcher redis = req.getRequestDispatcher("/employeeMenu.html");
			
			redis.forward(req, resp);
		}
	}
	
	public static void displayManagerMenu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ses = req.getSession(false);
		
		if(ses==null) {
			RequestDispatcher redis = req.getRequestDispatcher("/index.html");
			
			redis.forward(req, resp);
		} else {
			RequestDispatcher redis = req.getRequestDispatcher("/managerMenu.html");
			
			redis.forward(req, resp);
		}
	}

	public static void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession ses = req.getSession(false);
		
		log.debug(new Timestamp(System.currentTimeMillis()) + ": Employee " + ses.getAttribute("id") + " has logged out.");
		
		ses.invalidate();
		
		resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/expense/home");
	}
}
