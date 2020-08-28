<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link href="/css/reset.css" rel="stylesheet"></link>
	<link href="/css/style.css" rel="stylesheet"></link>
	<link href="/css/create-account.css" rel="stylesheet" />
	<title>Create Account</title>
</head>
<body>
	<header class="header-wrapper">
		<div>
			<h2>
				Shri Bank
			</h2>
		</div>
	</header>
	
	<div class="stable-design"></div>
	
	<section class="section-createAcc">
		<div class="heading">Create Account</div>
		<div class="error">
			<p>${errorMessage}</p>
		</div>

		<div class="">
			<form class="form-wrapper" action="/account" method="post">
				<div>
					<label>First Name :</label>
					<input type="text" name="firstName" /> 
				</div>
				<div>
					<label>Last Name :</label>
					<input type="text" name="lastName" />
				</div>
				<div>
					<label>Age</label>
					<input type="number" name="age" />
				</div>
				<div>
					<label>Opening Balance:</label>
					<input type="number" name="initaleBalance" />
				</div>
				<div>
					<label>Account Type :</label>
					<select name="accountType" id="accountType">
						<option value="SAVING_ACCOUNT">Saving Account</option>
						<option value="CURRENT_ACCOUNT">Current Account</option>
					</select>
				</div>
				<div>
					<label>Account Creator Id :</label>
					<input type="number" name="accountCreatorId" />
				</div>
				<div>
					<label>Withdraw Limit Per Day :</label>
					<input type="number" name="withdrawalLimitPerDay" />
				</div>
				<p class="submit-button">
					<input type="submit" value="Create Account >>"/>
				</p>
			</form>
		</div>
	</section>

</body>
</html>