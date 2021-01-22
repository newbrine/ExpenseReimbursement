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
import com.revature.model.RequestType;
import com.revature.model.Ticket;
import com.revature.service.ExpenseManager;

public class ManagerController {
	
	final static Logger log = Logger.getLogger(ManagerController.class);

	public static void managerHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher redis = req.getRequestDispatcher("/managerMenu.html");
		redis.forward(req, resp);
		
	}

	public static void createTicket(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ses = req.getSession(false);
		
		if(ses == null) {
			RequestDispatcher redis = req.getRequestDispatcher("/index.html");
			
			redis.forward(req, resp);
		} else {
			ExpenseManager manager = new ExpenseManager();
			
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
			
			Set<Ticket> pastTickets = ExpenseManager.getAllTickets();
			
			for(Ticket t: pastTickets) {
				jsonArray = jsonArray.concat(mapper.writeValueAsString(t) + ",");
			}
			
			jsonArray = jsonArray.substring(0, jsonArray.length()-1).concat("]");
			System.out.println(jsonArray);
			resp.getWriter().write(jsonArray);
		}
	}

	public static void approveTicket(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ses = req.getSession(false);
		
		if(ses == null) {
			RequestDispatcher redis = req.getRequestDispatcher("/index.html");
			
			redis.forward(req, resp);
		} else {
			int ticketId = Integer.parseInt(req.getParameter("ticketId"));
			Ticket t = ExpenseManager.getTicketById(ticketId);
			
			if(!ses.getAttribute("id").equals(t.getEmployeeId())) {
				log.debug(new Timestamp(System.currentTimeMillis()) + ": Employee " + ses.getAttribute("id") + " has approved Ticket " + ticketId);
				
				if(t.isWaiting()) {
					ExpenseManager.approveTicket(t);
				}
			}
		}
		
	}

	public static void denyTicket(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ses = req.getSession(false);
		
		if(ses == null) {
			RequestDispatcher redis = req.getRequestDispatcher("/index.html");
			
			redis.forward(req, resp);
		} else {
			int ticketId = Integer.parseInt(req.getParameter("ticketId"));
			Ticket t = ExpenseManager.getTicketById(ticketId);
			
			log.debug(new Timestamp(System.currentTimeMillis()) + ": Employee " + ses.getAttribute("id") + " has denied Ticket " + ticketId);
			
			if(t.isWaiting()) {
				ExpenseManager.denyTicket(t);
			}
		}
		
	}

}
