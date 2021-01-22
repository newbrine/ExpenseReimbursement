package com.revature.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Ticket;
import com.revature.service.ExpenseManager;

public class EmployeeController {
	
	static ExpenseManager manager = new ExpenseManager();
	final static Logger log = Logger.getLogger(EmployeeController.class);

	public static void employeeHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher redis = req.getRequestDispatcher("/employeeMenu.html");
		redis.forward(req, resp);
		
	}
	
	public static void createTicket(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ses = req.getSession(false);
		
		if(ses == null) {
			RequestDispatcher redis = req.getRequestDispatcher("/index.html");
			
			redis.forward(req, resp);
		} else {
			Ticket t = new Ticket(Integer.parseInt(req.getParameter("employeeId")), req.getParameter("type"), Double.parseDouble(req.getParameter("amount")),
					req.getParameter("description"), new Timestamp(System.currentTimeMillis()).toLocaleString(), false,false,true);
			
			log.debug(new Timestamp(System.currentTimeMillis()) + ": Employee " + ses.getAttribute("id") + " has created a ticket.");
			
			manager.createTicket(t);
		}
		
	}

	public static void viewPastTickets(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession ses = req.getSession(false);
		
		if(ses == null) {
			RequestDispatcher redis = req.getRequestDispatcher("/index.html");
			
			redis.forward(req, resp);
		} else {
			ObjectMapper mapper = new ObjectMapper();
			String jsonArray = "[";
			
			Set<Ticket> pastTickets = manager.getUserTickets((int) ses.getAttribute("id"));
			
			for(Ticket t: pastTickets) {
				jsonArray = jsonArray.concat(mapper.writeValueAsString(t) + ",");
			}
			
			jsonArray = jsonArray.substring(0, jsonArray.length()-1).concat("]");
			System.out.println(jsonArray);
			resp.getWriter().write(jsonArray);
		}
	}
}
