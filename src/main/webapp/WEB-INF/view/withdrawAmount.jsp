<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>withdraw amount</title>
</head>
<body>
	<h1>withdraw from account</h1>
	<font color="blue">${successMessage}</font>
	<font color="red">${errorMessage}</font>
	 <form action="/api/account/withdraw" method="post">
        account number : <input type="text" name="fromAccountId" />  <br/> 
        withdraw amount : <input type="number" name="amount" />  <br/>
        employee id :  <input type="number" name="bankEmployeeId" />  <br/> 
        <input type="submit" />
    </form>

</body>
</html>