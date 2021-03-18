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
	<h1 style="color: white; text-align: center">ExoBook - Admin</h1>
	<div align="center"
		style="border: solid; margin-top: 50px; margin-left: 300px; margin-right: 300px; background-color: white">

		<h1 style="color: #4251f5">
			Ciao
			<c:out value="${adminAttivo.username}" />
		</h1>
		<h3 style="color: red">
			<c:out value="${avviso}" />
		</h3>
		<c:if test="${adminAttivo.rango eq 'Amministratore'}">
		<c:url var="url" value="/admin/visualizzaIscritti" />
		<form:form action="${url}" method="post">
			<input type="submit" value="Visualizza Iscritti">
		</form:form>
		</c:if>
		<c:url var="url" value="/admin/visualizzaSegnalazioni" />
		<form:form action="${url}" method="POST">
			<c:choose>
				<c:when test="${nuoveSegnalazioni != 0}">
					<h5 style="color: red">
						<c:out value="Ci sono ${nuoveSegnalazioni} nuove segnalazioni" />
					</h5>
				</c:when>
				<c:otherwise>
					<br>
				</c:otherwise>
			</c:choose>
			<input type="submit" value="Visualizza Segnalazioni">
		</form:form>
		<br>
		<c:url var="url" value="/user/logout" />
		<form:form action="${url}">
			<input type="submit" value="Logout">
		</form:form>
		<br>
		<c:if
			test="${utenti != null && adminAttivo.rango eq 'Amministratore'}">
			<table cellspacing="50px" cellpadding="10px" border="4">
				<c:forEach var="user" items="${utenti}">
					<tr>
						<td><c:if test="${user.id == idMod}">
						<c:out value="${esito}"/> <br>
						</c:if><c:out value="${user.username}" /></td>
						<td><c:url var="url" value="/admin/nominaAdmin?id=${user.id}" /> <form:form
								action="${url}" method="POST" modelAttribute="amministratore">
								<form:hidden path="username" value="${user.username}" />
								<form:hidden path="password" value="${user.password}" />
								<form:hidden path="codiceSicurezza"
									value="Exo${user.username}1996"/>
								<input type="submit" value="Nomina Admin">
							</form:form></td>
					</tr>
				</c:forEach>
			</table>
			<br>
		</c:if>
	</div>
</body>
</html>