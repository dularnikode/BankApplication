<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Do Transaction</title>
</head>
<body>
	<h1>Do Transaction</h1>
	<font color="green">${successMessage}</font>
	<font color="red">${errorMessage}</font>
	 <form action="/api/account/transferAmount" method="post">
        amount : <input type="number" name="amount" />  <br/>
        sender account number : <input type="text" name="fromAccountId" />  <br/> 
        receiver account number : <input type="text" name="toAccountId" />  <br/>
        employee id :  <input type="number" name="bankEmployeeId" />  <br/> 
        <input type="submit" />
    </form>

</body>
</html>