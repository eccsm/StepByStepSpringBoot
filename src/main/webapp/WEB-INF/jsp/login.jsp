<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<html>

<body>
	<h1>Login</h1>
	<font color="red">${errorMessage}</font>
	<form method='POST' action="/login">
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit" value="submit" /></td>
			</tr>
		</table>
	</form>
</body>

</html>
<%@ include file="common/footer.jspf"%>