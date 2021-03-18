<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>
</head>
<body bgcolor="#4251f5">
	<h1 style="color: white; text-align: center">ExoBook</h1>
	<div align="center"
		style="border: solid; margin-top: 50px; margin-left: 300px; margin-right: 300px; background-color: white">
		<c:url var="url" value="/post/pubblica" />
		<form:form action="${url}" method="POST" modelAttribute="post">
			<form:hidden path="utente.id" value="${utenteAttivo.id}" />
			<h3 style="color: #4251f5">Il tuo Post</h3>
			<form:errors path="testo" cssStyle="color:#ff0000;" />
			<br>
			<form:textarea path="testo" maxlength="100"
				placeholder="Max 100 caratteri" />
			<br>
			<br>
			<input type="submit" value="Condividi">
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