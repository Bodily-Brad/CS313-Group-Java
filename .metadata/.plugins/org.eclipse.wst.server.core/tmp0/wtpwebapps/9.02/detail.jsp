<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>9.02 Movie Details</title>
</head>
<body>
	<h1>Movie Details</h1>
	<strong>${results.Title}</strong> (${results.Year})<br>
	Rating: ${results.Rated}<br>
	Runtime: ${results.Runtime }<br>
	<h2>Plot</h2>
	${results.Plot }<br>
     <br/><a href="search.jsp">Back To Search</a><br><br>
</body>
</html>
