package com.revature.service;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.revature.dao.TicketDao;
import com.revature.model.Ticket;

public class ExpenseManagerTest {

	@Test
	public void testCheckCredentials() {
		String email = "newbrine@gmail.com";
		String password = "password";
		
		assertTrue(ExpenseManager.checkCredentials(email, password));
	}

}
