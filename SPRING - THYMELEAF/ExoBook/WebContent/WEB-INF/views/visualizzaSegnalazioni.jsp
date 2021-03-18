<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Messaggi Ricevuti</title>
</head>
<body bgcolor="#4251f5">
	<h1 style="color: white; text-align: center">ExoBook - Admin</h1>
	<div align="center"
		style="border: solid; margin-top: 50px; margin-left: 50px; margin-right: 50px; background-color: white">
		<c:choose>
			<c:when test="${segnalazioni.size() == 0}">
				<h3 style="color: #4251f5">Non ci sono nuove segnalazioni</h3>
				<c:url var="url" value="/admin/indietro" />
				<form:form action="${url}" method="post">
					<input type="submit" value="Indietro" />
				</form:form>
				<br>
			</c:when>
			<c:otherwise>
				<h1 style="color: #4251f5">Segnalazioni</h1>
				<table cellspacing="50px" cellpadding="10px" border="4">
					<tr>
						<td><h3>SEGNALATO</h3></td>
						<td><h3>SEGNALATORE</h3></td>
						<td><h3>POST</h3></td>
						<td><h3>MOTIVAZIONE</h3></td>
						<td><h3>STATO</h3></td>
						<td><c:url var="url" value="/admin/indietro" /> <form:form
								action="${url}" method="post">
								<input type="submit" value="Indietro" />
							</form:form></td>
					</tr>
					<c:forEach var="seg" items="${segnalazioni}">
						<tr>
							<td><c:out value="${seg.utenteSegnalato.username}" /></td>
							<td><c:out value="${seg.utenteSegnalatore.username}" /></td>
							<td><c:out value="${seg.postSegnalato.testo}" /></td>
							<td><c:out value="${seg.motivazione}" /></td>
							<td><c:out value="${seg.esito}" /></td>
							<c:if test="${seg.esito eq 'In Attesa'}">
								<td><c:url var="url"
										value="/segnalazioni/risolvi?op=accetta" /> <form:form
										action="${url}" method="POST" modelAttribute="segnalazione">
										<form:hidden path="id" value="${seg.id}" />
										<input type="submit" value="Accetta">
									</form:form></td>
								<td><c:url var="url"
										value="/segnalazioni/risolvi?op=respingi" /> <form:form
										action="${url}" method="POST" modelAttribute="segnalazione">
										<form:hidden path="id" value="${seg.id}" />
										<input type="submit" value="Respingi">
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