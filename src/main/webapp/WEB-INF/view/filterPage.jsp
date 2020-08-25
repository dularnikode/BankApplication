<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<li><h3>
				<a href="/account/viewAll?sort=firstName">view all account
					sort by name</a>
			</h3>
		</li>
		<li>
			<h3>
				<a href="/account/viewAll?sort=balance">view all account
					sort by available balance</a>
			</h3>
		</li>
		<li>
			<h3>
				<a href="/account/viewAll/minBalance">view all account
					balance is more than min balance</a>
			</h3>
		</li>
	</ul>
</body>
</html>