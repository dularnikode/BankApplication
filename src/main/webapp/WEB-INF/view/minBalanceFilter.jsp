<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Do Transaction</title>
</head>
<body>
	<h1>Do filter with minimum balance</h1>
	<font color="green">${successMessage}</font>
	<font color="red">${errorMessage}</font>
	 <form action="/account/viewAll" method="get">
        min balance : <input type="number" name="minBalance" />  <br/>
        <input type="submit" />
    </form>
</body>
</html>