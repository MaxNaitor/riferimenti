<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Segnalazioni</title>
</head>
<body bgcolor="#4251f5">
	<h1 style="color: white; text-align: center">ExoBook</h1>
	<div align="center"
		style="border: solid; margin-top: 50px; margin-left: 50px; margin-right: 50px; background-color: white">
		<c:choose>
			<c:when test="${messaggi.size() == 0}">
				<h3 style="color: #4251f5">Non ci sono nuovi messaggi</h3>
				<c:url var="url" value="/indietroUtente" />
				<form:form action="${url}" method="post">
					<input type="submit" value="Indietro" />
				</form:form>
				<br>
			</c:when>
			<c:otherwise>
				<h1 style="color: #4251f5">I tuoi messaggi</h1>
				<table cellspacing="50px" cellpadding="10px" border="4">
					<tr>
						<td><h3>MITTENTE</h3></td>
						<td><h3>MESSAGGIO</h3></td>
						<td><c:url var="url" value="/indietroUtente" /> <form:form
								action="${url}" method="post">
								<input type="submit" value="Indietro" />
							</form:form></td>
					</tr>
					<c:forEach var="msg" items="${messaggi}">
						<tr>
							<td><c:out value="${msg.mittente.username}" /></td>
							<td><c:out value="${msg.messaggio}" /></td>
							<c:choose>
								<c:when test="${msg.stato eq 'Non Letto'}">
									<td><c:url var="url" value="/messaggi/segnaLetto" /> <form:form
											action="${url}" method="POST" modelAttribute="messaggio">
											<form:hidden path="id" value="${msg.id}" />
											<input type="submit" value="Segna come letto">
										</form:form></td>
								</c:when>
								<c:otherwise>
									<td><c:url var="url" value="/messaggi/cancella" /> <form:form
											action="${url}" method="POST" modelAttribute="messaggio">
											<form:hidden path="id" value="${msg.id}" />
											<input type="submit" value="Cancella">
										</form:form></td>
								</c:otherwise>
							</c:choose>
							<c:if test="${msg.mittente.id != 999}">
								<td><c:url var="url"
										value="/messaggi/messaggiForm?idMsg=${msg.id}" /> <form:form
										action="${url}" method="POST" modelAttribute="destinatario">
										<form:hidden path="id" value="${msg.mittente.id}" />
										<form:hidden path="username" value="${msg.mittente.username}" />
										<input type="submit" value="Rispondi">
									</form:form></td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
		<br>
	</div>
</body>
</html>