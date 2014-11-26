<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Person Details</title>
</head>

<body>
	<H1>Details</H1>
	<strong>${person.first } ${person.last }</strong><br>
	Born: ${person.birth }<br>
	<h2>Parents</h2>
	Father: ${person.father.first }<br>
	Mother: ${person.mother.first }
	<h2>Children</h2>
		<c:forEach var="child" items="${person.children}">
			${child.first}<br>
		</c:forEach>	
	<a href="Search">Return to List</a>
</body>
</html>
