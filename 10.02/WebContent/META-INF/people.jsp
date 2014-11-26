<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>People Search</title>
</head>

<script type="text/javascript">
	function link(first, last, birth)
	{
		//set form variables
		document.getElementById("first").value = first;
		document.getElementById("last").value = last;
		document.getElementById("birth").value = birth;
		
		document.getElementById("form").submit();
	}
</script>


<body>

	<h1>People Search</h1>
      	 <c:forEach items="${list}" var="line">
      		<a href="javascript:link('${line.getFirst()}', '${line.getLast()}' , '${line.getBirth()}');">${line.getFirst()} ${line.getLast()}</a><br/>
       	 </c:forEach>
       	 
       	 
     <form action="details.jsp" method="Post"  id ="form" style="display:none">
			<input type="text" name="first" id="first"><br>
			<input type="text" name="last" id="last"><br>
			<input type="text" name="birth" id ="birth"><br>
		<input type="submit" value="Login">
	 </form>
	 
</body>
</html>
