<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Messaggi Form</title>
</head>
<body bgcolor="#4251f5">
	<h1 style="color: white; text-align: center">ExoBook</h1>
	<div align="center"
		style="border: solid; margin-top: 50px; margin-left: 300px; margin-right: 300px; background-color: white">
		<h1 style="color:#4251f5">
			<c:out value="Messaggio per ${destinatario.username}" />
		</h1>
		<c:url var="url" value="/messaggi/invia" />
		<form:form action="${url}" method="POST" modelAttribute="messaggio">
			<form:hidden path="mittente.id" value="${utenteAttivo.id}" />
			<form:hidden path="destinatario.id" value="${destinatario.id}" />
			<label>Messaggio</label>
			<br>
			<form:errors path="messaggio" cssStyle="color:#ff0000;"/>
			<br>
			<br>
			<form:textarea path="messaggio" maxlength="100"
				placeholder="Max 100 caratteri" />
			<br>
			<br>
			<input type="submit" value="Invia" />
		</form:form>
		<br>
		<c:url var="url" value="/indietroUtente" />
		<form:form action="${url}" method="post">
			<input type="submit" value="Indietro" />
		</form:form>
		<br>
	</div>
</body>
</html>