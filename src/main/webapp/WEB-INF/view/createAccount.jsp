<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Account</title>
</head>
<body>
	<h1>Create Account</h1>
	<font color="red">${errorMessage}</font>
	 <form action="/api/account" method="post">
        First Name : <input type="text" name="firstName" />  <br/>
        Last Name : <input type="text" name="lastName" />  <br/>
        Opening amount : <input type="number" name="initaleBalance" />  <br/>
        Account type : <input type="text" name="accountType" />  <br/>
        account creator id : <input type="number" name="accountCreatorId" />  <br/>
        withdraw limit per day : <input type="number" name="withdrawalLimitPerDay" />  <br/>
        <input type="submit" />
    </form>

</body>
</html>