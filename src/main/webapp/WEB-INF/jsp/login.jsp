<%@ include file="common/header.jspf"%>

<html>
<body>
	<h1>Login</h1>
	<font color="red">${errorMessage}</font>
	<font color="green">${registrationMessage}</font>
	<form method='POST' action="/login">
		<table>
			<tr>
				<td><input type='text' name='username' placeholder="Username"
					required>
			</tr>
			<tr>
				<td><input type='password' name='password'
					placeholder="Password" required /></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit" value="submit" /></td>
			</tr>
		</table>
	</form>

	<label> If you don't have account <a href="/register">Click
			here</a> to create.
	</label>
</body>

</html>
<%@ include file="common/footer.jspf"%>