package com.revature.dao;

import java.util.Set;

import com.revature.model.Ticket;

public interface TicketDao {
	
	//CRUD
	
	//Create
	public boolean insertTicket(Ticket t);
	
	//Retrieve
	public Ticket selectTicketById(int id);
	public Set<Ticket> selectAllTicket();
	public Set<Ticket> selectApprovedTickets();
	public Set<Ticket> selectDeniedTickets();
	public Set<Ticket> selectPendingTickets();
	
	//Update
	public boolean approveTicket(Ticket t);
	public boolean denyTicket(Ticket t);
	
	//Delete
	public boolean deleteTicket(Ticket t);
	public boolean deleteTicketById(int id);

}
