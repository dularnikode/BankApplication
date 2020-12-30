  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
      pageEncoding="ISO-8859-1"%>
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="ISO-8859-1">
  <title>account details</title>
  </head>
  <body>
      <h1>Account Details</h1>
      <font color="red">${errorMessage}</font>
      <h3>account details of account number :  ${account.accountId}</h3>
          Account number : ${account.accountId} <br/>
          First Name : ${account.firstName} <br/>
          Last Name : ${account.lastName} <br/>
          Age : ${account.age}<br/>
          Balance : ${account.balance} <br/>
          Account type : ${account.accountType} <br/>
          account creator : ${account.firstName}  ${account.lastName}<br/>
          withdraw limit per day : ${account.withdrawalLimitPerDay}<br/>

  </body>
  </html>