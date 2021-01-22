package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.revature.model.RequestType;
import com.revature.model.Ticket;
import com.revature.utils.ConnectionFactory;

public class TicketDaoImpl implements TicketDao {

	@Override
	public boolean insertTicket(Ticket t) {
		boolean result = false;
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "INSERT INTO expense_tickets (employee_id,request_type,request_amount,description,"
					+ "submit_time,approved,denied,waiting) values (?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareCall(sql);
			
			ps.setInt(1, t.getEmployeeId());
			ps.setString(2, t.getRequestType());
			ps.setDouble(3, t.getRequestAmount());
			ps.setString(4, t.getDescription());
			ps.setString(5, t.getTimestamp());
			ps.setBoolean(6, t.isApproved());
			ps.setBoolean(7, t.isDenied());
			ps.setBoolean(8, t.isWaiting());
			
			result = ps.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Ticket selectTicketById(int id) {
		List<Ticket> tickets = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * FROM expense_tickets WHERE ticket_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				tickets.add(new Ticket(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4),
						rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getBoolean(8), rs.getBoolean(9)));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return tickets.get(0);
	}

	@Override
	public Set<Ticket> selectAllTicket() {
		Set<Ticket> tickets = new HashSet<>();
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * FROM expense_tickets";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				tickets.add(new Ticket(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4),
						rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getBoolean(8), rs.getBoolean(9)));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return tickets;
	}

	@Override
	public Set<Ticket> selectApprovedTickets() {
		Set<Ticket> tickets = new HashSet<>();
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * FROM expense_tickets WHERE approved=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setBoolean(1, true);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				tickets.add(new Ticket(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4),
						rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getBoolean(8), rs.getBoolean(9)));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return tickets;
	}

	@Override
	public Set<Ticket> selectDeniedTickets() {
		Set<Ticket> tickets = new HashSet<>();
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * FROM expense_tickets WHERE denied=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setBoolean(1, true);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				tickets.add(new Ticket(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4),
						rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getBoolean(8), rs.getBoolean(9)));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return tickets;
	}

	@Override
	public Set<Ticket> selectPendingTickets() {
		Set<Ticket> tickets = new HashSet<>();
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * FROM expense_tickets WHERE waiting=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setBoolean(1, true);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				tickets.add(new Ticket(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4),
						rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getBoolean(8), rs.getBoolean(9)));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return tickets;
	}

	@Override
	public boolean approveTicket(Ticket t) {
		boolean result = false;
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "CALL approve_ticket(?)";
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setInt(1, t.getTicketId());
			
			cs.executeUpdate();
			result=true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean denyTicket(Ticket t) {
		boolean result = false;
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "CALL deny_ticket(?)";
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setInt(1, t.getTicketId());
			
			result = cs.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean deleteTicket(Ticket t) {
		boolean result = false;
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "DELETE FROM expense_tickets WHERE ticket_id=?";
			PreparedStatement ps = conn.prepareCall(sql);
			
			ps.setInt(1, t.getTicketId());
			
			result = ps.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean deleteTicketById(int id) {
		boolean result = false;
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "DELETE FROM expense_tickets WHERE ticket_id=?";
			PreparedStatement ps = conn.prepareCall(sql);
			
			ps.setInt(1, id);
			
			result = ps.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
