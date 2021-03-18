<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Form</title>
</head>
<body bgcolor="#4251f5">
	<h1 style="color: white; text-align: center">ExoBook</h1>
	<div align="center"
		style="border: solid; margin-top: 50px; margin-left: 300px; margin-right: 300px; background-color: white">
		<h1 style="color:#4251f5">
			<c:out value="Accesso Admin" />
		</h1>
		<h3 style="color:red"><c:out value="${messaggio}"/></h3>
		<c:url var="url" value="/admin/accesso" />
		<form:form action="${url}" method="post" modelAttribute="amministratore">
			<label>Username</label>
			<br>
			<form:errors path="username" cssStyle="color:#ff0000;"/>
			<br>
			<form:input path="username" />
			<br><br>
			<label>Password</label>
			<br>
			<form:errors path="password" cssStyle="color:#ff0000;"/>
			<br>
			<form:input type="password" path="password" />			
			<br><br>
			<label>Codice di Sicurezza</label>
			<br>
			<form:errors path="codiceSicurezza" cssStyle="color:#ff0000;"/>
			<br>
			<form:input path="codiceSicurezza" type="password" />
			<br><br>
			<label>Amministratore</label>
			<form:radiobutton path="rango" value="Amministratore"/>
			<br>
			<label>Moderatore</label>
			<form:radiobutton path="rango" value="Moderatore"/>
			<br><br>
			<input type="submit" value="Invia" />
		</form:form>
		<br>
		<c:url var="url" value="/index"/>
		<form:form action="${url}" method="post" >
			<input type="submit" value="Indietro" />
		</form:form>
		<br>
	</div>
</body>
</html>