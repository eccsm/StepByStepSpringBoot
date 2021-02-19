<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<%@ include file="common/header.jspf"%>
<body>
	<h1>Register</h1>



	<form:form action="register" modelAttribute="orderer" method="post">
		<table style="with: 50%">
			<tr>
				<td><form:input path="username" type="text"
						placeholder="username" /> <form:errors path="username"
						cssClass="error" /></td>
			</tr>

			<tr>
				<td><form:input path="email" type="email" placeholder="Email" />
					<form:errors path="email" cssClass="error" /></td>
			</tr>


			<tr>
				<td><form:input path="password" type="password"
						placeholder="password" /> <form:errors path="password"
						cssClass="error" /></td>
			</tr>
		</table>
		<input type="submit" value="Submit" />
	</form:form>
</body>


<%@ include file="common/footer.jspf"%>

</html>