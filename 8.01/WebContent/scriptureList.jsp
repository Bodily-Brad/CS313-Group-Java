<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Scripture List</title>
</head>
<body>
	<div>
		<h1>Static Content</h1>
		<h2>Scriptures</h2>
		Proverbs 3:5<br>
		Doc. &amp; Cov. 112:10<br>
		John 7:17<br>
	</div>
	<div>
		<h1>Dynamic Content</h1>
		<h2>Scriptures</h2>
	<%
		List<String> scriptures = new ArrayList<String>();
		scriptures.add("Proverbs 3:5");
		scriptures.add("Doc. &amp; Cov. 112:10");
		scriptures.add("John 7:17");
		
		for (String scripture : scriptures )
		{
			out.println(scripture + "<br>");
		}
	%>
	</div>
</body>
</html>