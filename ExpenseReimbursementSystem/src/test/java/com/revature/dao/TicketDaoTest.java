package com.revature.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.model.Ticket;

public class TicketDaoTest {

	@Test
	public void testSelectById() {
		Ticket test = new Ticket(3,12,"Food",12.43,"Burger","Jan 12, 2021, 9:56:21 PM",true,false,false);
		Ticket data = new TicketDaoImpl().selectTicketById(3);
		assertEquals(test, data);		
	}
	


}
