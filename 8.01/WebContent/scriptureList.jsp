<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Scripture List</title>
</head>
<body>
	<div>
		<h1>Dynamic Content from Servlet</h1>
		<h2>Scriptures</h2>
		<c:forEach var="scripture" items="${scriptures}">
			<strong>${scripture.book}</strong> ${scripture.chapter}:${scripture.verse}<br>
		</c:forEach>
	</div>
</body>
</html>