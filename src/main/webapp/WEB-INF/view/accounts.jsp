<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>view all accounts</title>
</head>
<body>

<h1>accounts detail</h1><br/>

	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<th>Account number</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Balance</th>
			<th>Account type</th>
			<th>Account Creator</th>
			<th>Withdraw limit per day</th>
		</tr>

		<c:forEach var="account" items="${accounts}">
			<tr>
				<td>${account.accountId}</td>
				<td>${account.firstName}</td>
				<td>${account.lastName}</td>
				<td>${account.balance}</td>
				<td>${account.accountType}</td>
				<td>${account.firstName}${account.lastName}</td>
				<td>${account.withdrawalLimitPerDay}</td>
			</tr>
		</c:forEach>
	</table>

	<%--For displaying Previous link except for the 1st page --%>
	<c:if test="${currentPage != 1}">
		<td><a href="/api/account/viewAll?page=${currentPage - 1}">Previous</a></td>
	</c:if>

	<%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<c:forEach begin="1" end="${noOfPages}" var="i">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<td>${i}</td>
					</c:when>
					<c:otherwise>
						<td><a href="/api/account/viewAll?page=${i-1}">${i-0}</a></td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	</table>

	<%--For displaying Next link --%>
	<c:if test="${currentPage lt noOfPages}">
		<td><a href="/api/account/viewAll?page=${currentPage + 1}">Next</a></td>
	</c:if>
</body>
</html>