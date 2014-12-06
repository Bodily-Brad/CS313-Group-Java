<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Get Location</title>
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
			window.location = "../ShowLocations";
		}
		
	</script>
</head>
<body>
	<h1>Do you want to add your current location?</h1>
	
	<input type="button" value="Yes" onclick="saveLocation()">
	<input type="button" value="No" onclick="forward()"><br>
	
	<form action="../SaveLocation" method="Post"  id ="form" style="display:none">
			<input type="text" name="latitude" id="latitude"><br>
			<input type="text" name="longitude" id="longitude"><br>
		<input type="submit" value="Login">
	 </form>
	  
</body>
</html>
