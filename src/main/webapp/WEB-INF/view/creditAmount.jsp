<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>add balance</title>
</head>
<body>
	<h1>Add balance</h1>
	<font color="green">${successMessage}</font>
	<font color="red">${errorMessage}</font>
	 <form action="/api/account/credit" method="post">
        account number : <input type="text" name="fromAccountId" />  <br/> 
        credit amount : <input type="number" name="amount" />  <br/>
        employee id :  <input type="number" name="bankEmployeeId" />  <br/> 
        <input type="submit" />
    </form>

</body>
</html>