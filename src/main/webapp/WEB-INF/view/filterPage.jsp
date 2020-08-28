<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Filter Page</title>
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
		
		<li>
			<h3>
				<a href="/account/viewAll/ageGreaterThan60AndIfCredited2000BalanceMoreThan5000">Age
					greater than 60 and if credit 2000 than balance become more than
					5000 
				</a>
			</h3>
		</li>
		
	</ul>
</body>
</html>