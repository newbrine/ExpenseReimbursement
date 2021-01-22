window.onload = function(){
	getTickets();
};

function getTickets(){
	let xhttp = new XMLHttpRequest();
	
	let getOldURL = "http://localhost:8080/ExpenseReimbursementSystem/expense/manViewPast";
	
	xhttp.open("GET",getOldURL);
	
	xhttp.responseType = "json";
	
	xhttp.send();
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4 && xhttp.status == 200){
			console.log(xhttp.response);
			updateTable(xhttp.response);
		}
	}
}

function updateTable(response){
	let table = document.getElementById("pastTicketTable");
	
	for(x in response){
		let row = table.insertRow(1);
	
		let ticketId = row.insertCell(0);
		let employeeId = row.insertCell(1);
		let requestAmount = row.insertCell(2);
		let requestType = row.insertCell(3);
		let description = row.insertCell(4);
		let approved = row.insertCell(5);
		let denied = row.insertCell(6);
		let pending = row.insertCell(7);
		
		ticketId.innerHTML = response[x].ticketId;
		employeeId.innerHTML = response[x].employeeId;
		requestAmount.innerHTML = response[x].requestAmount;
		requestType.innerHTML = response[x].requestType;
		description.innerHTML = response[x].description;
		
		if(response[x].approved){
			approved.innerHTML = response[x].approved
		}
		
		if(response[x].denied){
			denied.innerHTML = response[x].denied
		}
		
		if(response[x].waiting){
			pending.innerHTML = response[x].waiting
		}
	}
}

function createTicket(){
	let xhttp = new XMLHttpRequest();

    let createURL = "http://localhost:8080/ExpenseReimbursementSystem/expense/manCreateTicket";

    let employeeId = document.getElementById("employeeId").value;
    let amount = document.getElementById("requestAmount").value;
    let type = document.getElementById("requestType").value;
	let description = document.getElementById("description").value;

    let params = `employeeId=${employeeId}&amount=${amount}&type=${type}&description=${description}`;

    xhttp.open("POST",createURL);

    xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

    xhttp.onreadystatechange = function(){
        if(xhttp.readyState == 4 && xhttp.status == 200){
			window.alert("Successfully Sent.");
			
			let reset = document.getElementById("createForm").reset();
        }
    }
    xhttp.send(params);
}

function approveTicket(){
	let xhttp = new XMLHttpRequest();

    let createURL = "http://localhost:8080/ExpenseReimbursementSystem/expense/approve";

    let ticketId = document.getElementById("ticketId").value;

    let params = `ticketId=${ticketId}`;

    xhttp.open("POST",createURL);

    xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

    xhttp.onreadystatechange = function(){
        if(xhttp.readyState == 4 && xhttp.status == 200){
			window.alert("Successfully Sent.");
			
			let reset = document.getElementById("createForm").reset();
        }
    }
    xhttp.send(params);
}

function denyTicket(){
	let xhttp = new XMLHttpRequest();

    let createURL = "http://localhost:8080/ExpenseReimbursementSystem/expense/deny";

    let ticketId = document.getElementById("ticketId").value;

    let params = `ticketId=${ticketId}`;

    xhttp.open("POST",createURL);

    xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

    xhttp.onreadystatechange = function(){
        if(xhttp.readyState == 4 && xhttp.status == 200){
			window.alert("Successfully Sent.");
			
			let reset = document.getElementById("createForm").reset();
        }
    }
    xhttp.send(params);
}