package com.revature.model;

public class Ticket {
	
	private int ticketId;
	private int employeeId;
	private String requestType;
	private double requestAmount;
	private String description;
	private String timestamp;
	private boolean approved;
	private boolean denied;
	private boolean waiting;
	
	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Ticket(int employeeId, String string, double requestAmount, String description, String timestamp,
			boolean approved, boolean denied, boolean waiting) {
		super();
		this.employeeId = employeeId;
		this.requestType = string;
		this.requestAmount = requestAmount;
		this.description = description;
		this.timestamp = timestamp;
		this.approved = approved;
		this.denied = denied;
		this.waiting = waiting;
	}
	
	public Ticket(int ticketId, int employeeId, String requestType, double requestAmount, String description,
			String timestamp, boolean approved, boolean denied, boolean waiting) {
		super();
		this.ticketId = ticketId;
		this.employeeId = employeeId;
		this.requestType = requestType;
		this.requestAmount = requestAmount;
		this.description = description;
		this.timestamp = timestamp;
		this.approved = approved;
		this.denied = denied;
		this.waiting = waiting;
	}
	
	public int getTicketId() {
		return ticketId;
	}
	
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getRequestType() {
		return requestType;
	}
	
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
	public double getRequestAmount() {
		return requestAmount;
	}
	
	public void setRequestAmount(double requestAmount) {
		this.requestAmount = requestAmount;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public boolean isApproved() {
		return approved;
	}
	
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	public boolean isDenied() {
		return denied;
	}
	
	public void setDenied(boolean denied) {
		this.denied = denied;
	}
	
	public boolean isWaiting() {
		return waiting;
	}
	
	public void setWaiting(boolean waiting) {
		this.waiting = waiting;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (approved ? 1231 : 1237);
		result = prime * result + (denied ? 1231 : 1237);
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + employeeId;
		long temp;
		temp = Double.doubleToLongBits(requestAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((requestType == null) ? 0 : requestType.hashCode());
		result = prime * result + ticketId;
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + (waiting ? 1231 : 1237);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (approved != other.approved)
			return false;
		if (denied != other.denied)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (Double.doubleToLongBits(requestAmount) != Double.doubleToLongBits(other.requestAmount))
			return false;
		if (requestType != other.requestType)
			return false;
		if (ticketId != other.ticketId)
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (waiting != other.waiting)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", employeeId=" + employeeId + ", requestType=" + requestType
				+ ", requestAmount=" + requestAmount + ", description=" + description + ", timestamp=" + timestamp
				+ ", approved=" + approved + ", denied=" + denied + ", waiting=" + waiting + "]";
	}
	
}
