<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Post</title>
</head>
<body>
	<h1>Enter New Post</h1>
	<a href="ShowPosts">Show Posts</a><br>
	<br>
	<form method="POST" action="CreatePost">
		<label>Body</label><br>
		<textarea name="body"></textarea>
		<input type="hidden" name="author" value="${sessionScope.loggedInUser}">
		<br>
		<input type="submit" value="Submit">
	</form>
	<br>
	<a href="SignOut">Logout</a>
</body>
</html>