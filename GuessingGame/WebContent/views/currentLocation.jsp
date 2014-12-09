<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="/includes/_head.html"/>
<body>
	<div class='gameFrame'>
		<div class='gameNote'>
			Thank you for Playing
		</div>
        <div class='gameUserInputArea'>
            <div class='gameInstructions'>
				<c:if test="${message != null && !message.isEmpty()}">
					<p>${requestScope.message}</p>
				</c:if>
				<div class='gameQuestion'>Can we record your current location on our games played map?</div>
				<div class='gameQuestion'>(Your browser may prompt you to access to location services, clicking Allow will let us mark another pin on our map.)</div>
				<div class='gameButtonArea'>
					<input type="button" value="Yes" onclick="saveLocation()">
					<input type="button" value="No" onclick="forward()">
				</div>				
				<form action="SaveLocation" method="Post"  id ="form" style="display: none">
					<input type="text" name="latitude" id="latitude"><br>
					<input type="text" name="longitude" id="longitude"><br>
					<input type="submit" value="submit">
				</form>
            </div>           
        </div>
        <div class='gameNote'><a href="NewGame">Play Again</a></div>
	</div>
	<script>
		function saveLocation()
		{
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(showPosition);
		    } else {
		    	forward();
		    }
		}
		
		function showPosition(position) {
			document.getElementById("latitude").value = position.coords.latitude;
			document.getElementById("longitude").value = position.coords.longitude;
			document.getElementById("form").submit();
		}
		
		function forward()
		{
			window.location = "ShowLocations";
		}
		
	</script>	  
</body>
</html>
