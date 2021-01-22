function employeeLogin(){
    let xhttp = new XMLHttpRequest();

    let employeeLoginURL = "http://localhost:8080/ExpenseReimbursementSystem/expense/EmpLogin";

    xhttp.open("GET",employeeLoginURL);
    xhttp.send();
    
    xhttp.onreadystatechange = function(){
    	if(xhttp.readyState == 4 && xhttp.status==200){
    		window.location.assign("http://localhost:8080/ExpenseReimbursementSystem/expense/EmpLogin");
    	}
    }
}

function logout(){
	let xhttp = new XMLHttpRequest();

    let employeeLoginURL = "http://localhost:8080/ExpenseReimbursementSystem/expense/logout";

    xhttp.open("GET",employeeLoginURL);
    xhttp.send();
    
    xhttp.onreadystatechange = function(){
    	if(xhttp.readyState == 4 && xhttp.status==200){
    		window.location.assign("http://localhost:8080/ExpenseReimbursementSystem/expense/home");
    	}
    }
}