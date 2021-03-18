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
		<h1 style="color: #4251f5">
			Bacheca di
			<c:out value="${utente.username}" />
		</h1>
		<c:if test="${listaPost.size() == 0}">
			<h3>Non ci sono Post da visualizzare</h3>
		</c:if>
		<c:forEach var="p" items="${listaPost}">
			<hr>
			<h3>
				<c:out value="${p.testo}" />
			</h3>
			<br>
			<br>
			<c:url var="url" value="/post/aggiungiLike" />
			<form:form action="${url}" method="POST" modelAttribute="post">
				<form:hidden path="id" value="${p.id}" />
				<form:hidden path="utente.id" value="${p.utente.id}" />
				<form:hidden path="testo" value="${p.testo}" />
				<form:hidden path="likes" value="${p.likes}" />
				<input type="submit" value="Mi Piace: ${p.likes}">
			</form:form>
			<br>
			<c:choose>
				<c:when test="${p.utente.id == utenteAttivo.id}">
					<c:url var="url" value="/post/elimina" />
					<form:form action="${url}" method="POST" modelAttribute="post">
						<form:hidden path="id" value="${p.id}" />
						<form:hidden path="utente.id" value="${p.utente.id}" />
						<input type="submit" value="Elimina Post">
					</form:form>
				</c:when>
				<c:otherwise>
					<c:url var="url" value="/segnalazioni/segnala" />
					<form:form action="${url}" method="POST"
						modelAttribute="segnalazione">
						<form:hidden path="utenteSegnalato.id" value="${p.utente.id}" />
						<form:hidden path="utenteSegnalato.username"
							value="${p.utente.username}" />
						<form:hidden path="utenteSegnalatore.id"
							value="${utenteAttivo.id}" />
						<form:hidden path="postSegnalato.id" value="${p.id}" />
						<form:errors path="motivazione" cssStyle="color:#ff0000;" />
						<form:input path="motivazione"
							placeholder="Motiva la tua segnalazione" /> &nbsp
				<input type="submit" value="Segnala">
					</form:form>
				</c:otherwise>
			</c:choose>
			<br>
			<c:url var="url" value="/commenti/aggiungi" />
			<form:form action="${url}" method="POST" modelAttribute="commento">
				<form:hidden path="utente.id" value="${utenteAttivo.id}" />
				<form:hidden path="post.id" value="${p.id}" />
				<form:input path="testo" />
				<input type="submit" value="Commenta">
			</form:form>
			<br>
			<table cellspacing="50px" cellpadding="10px" border="4">
				<c:forEach var="comm" items="${commenti}">
					<c:if test="${comm.post.id == p.id}">
						<tr>
							<td><c:out value="${comm.utente.username}" /></td>
							<td><c:out value="${comm.testo}" /></td>
							<c:choose>
								<c:when test="${p.utente.id == utenteAttivo.id}">
									<c:url var="url" value="/commenti/elimina" />
									<td><form:form action="${url}" method="POST"
											modelAttribute="commento">
											<form:hidden path="id" value="${comm.id}" />
											<form:hidden path="utente.id" value="${comm.utente.id}" />
											<form:hidden path="post.id" value="${comm.post.id}" />
											<form:hidden path="testo" value="${comm.testo}" />
											<input type="submit" value="Elimina Commento">
										</form:form></td>
								</c:when>
								<c:otherwise>
									<c:if test="${comm.utente.id == utenteAttivo.id}">
										<c:url var="url" value="/commenti/elimina" />
										<td><form:form action="${url}" method="POST"
												modelAttribute="commento">
												<form:hidden path="id" value="${comm.id}" />
												<form:hidden path="utente.id" value="${comm.utente.id}" />
												<form:hidden path="post.id" value="${comm.post.id}" />
												<form:hidden path="testo" value="${comm.testo}" />
												<input type="submit" value="Elimina Commento">
											</form:form></td>
									</c:if>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:if>
				</c:forEach>
			</table>
			<hr>
		</c:forEach>
		<c:url var="url" value="/indietroUtente" />
		<form:form action="${url}" method="post">
			<input type="submit" value="Indietro" />
		</form:form>
		<br>
	</div>
</body>
</html>