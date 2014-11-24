<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show Posts</title>
</head>
<body>
	<h1>View Posts</h1>
	<a href="newPost.jsp">New Post</a><br>
	<br>	
	<div>
		<c:forEach var="post" items="${posts}">
			<strong>${post.authorUserName} - ${post.date}</strong><br>
			${post.content}<br><br>
		</c:forEach>
	</div>
	<div>
	<a href="SignOut">Logout</a>	
	</div>
</body>
</html>