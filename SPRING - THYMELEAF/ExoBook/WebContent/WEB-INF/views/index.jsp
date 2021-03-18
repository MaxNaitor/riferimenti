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
		<c:choose>
			<c:when test="${utenteAttivo == null}">
				<h1 style="color: #4251f5; text-align: center">Il Social per
					Developers</h1>
				<h3 style="color: red">
					<c:out value="${messaggio}" />
				</h3>
				<br>
				<c:url var="url" value="/user/login" />
				<form:form action="${url}">
					<input type="hidden" name="op" value="Registrati">
					<input type="submit" value="Registrati">
				</form:form>
				<br>
				<c:url var="url" value="/user/login" />
				<form:form action="${url}">
					<input type="hidden" name="op" value="Accedi">
					<input type="submit" value="Accedi">
				</form:form>
				<br>
				<c:url var="url" value="/admin/login" />
				<form:form action="${url}">
					<input type="submit" value="Accedi come Admin">
				</form:form>
				<br>
				<br>
			</c:when>
			<c:otherwise>
				<h1 style="color: #4251f5">
					Ciao
					<c:out value="${utenteAttivo.username}" />
				</h1>
				<h3 style="color: red">
					<c:out value="${avviso}" />
				</h3>
				<c:url var="url" value="/user/visualizzaIscritti" />
				<form:form action="${url}">
					<input type="submit" value="Visualizza Iscritti">
				</form:form>
				<c:url var="url" value="/messaggi/messaggiRicevuti" />
				<form:form action="${url}" method="POST">
					<c:choose>
						<c:when test="${nuoviMessaggi != 0}">
							<h5 style="color: red">
								<c:out value="Hai ${nuoviMessaggi} messaggi da leggere" />
							</h5>
						</c:when>
						<c:otherwise>
							<br>
						</c:otherwise>
					</c:choose>
					<input type="submit" value="Leggi messaggi">
				</form:form>
				<br>
				<c:url var="url" value="/post/form" />
				<form:form action="${url}" method="POST">
					<input type="submit" value="Crea post">
				</form:form>
				<br>
				<c:url var="url" value="/post/visualizzaBacheca" />
				<form:form action="${url}" method="POST" modelAttribute="utente">
					<form:hidden path="id" value="${utenteAttivo.id}" />
					<input type="submit" value="Visualizza la tua Bacheca">
				</form:form>
				<br>
				<c:url var="url" value="/user/logout" />
				<form:form action="${url}">
					<input type="submit" value="Logout">
				</form:form>
				<br>
			</c:otherwise>
		</c:choose>
		<c:if test="${utenti != null}">
			<table cellspacing="50px" cellpadding="10px" border="4">
				<c:forEach var="user" items="${utenti}">
					<tr>
						<td><c:out value="${user.username}" /></td>
						<td><c:url var="url" value="/messaggi/messaggiForm?idMsg=0" /> <form:form
								action="${url}" method="POST" modelAttribute="destinatario">
								<form:hidden path="id" value="${user.id}" />
								<form:hidden path="username" value="${user.username}" />
								<input type="submit" value="Invia Messaggio">
							</form:form></td>
						<td><c:url var="url" value="/post/visualizzaBacheca" /> <form:form
								action="${url}" method="POST" modelAttribute="utente">
								<form:hidden path="id" value="${user.id}" />
								<input type="submit" value="Visualizza Bacheca">
							</form:form></td>
					</tr>
				</c:forEach>
			</table>
			<br>
		</c:if>
	</div>
</body>
</html>