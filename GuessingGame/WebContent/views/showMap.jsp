<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <link rel="stylesheet" type="text/css" href="style/bcb.css" media="screen">

<script
	src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCrkJAZZAeEnD90ThXYsfaEgbjW0NQ6XTQ&amp;sensor=true"
	type="text/javascript">
	
</script>

<script type="text/javascript">
	function initialize() {
		var mapProp = {
			//Center the map on Rexburg, ID
			center : new google.maps.LatLng(43.8, -111.8),
			zoom : 4,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		//Initialize the map with a Google Maps Object
		var map = new google.maps.Map(document.getElementById("googleMap"),
				mapProp);

		//Get the locations from the locations variable
		<c:forEach var="location" items="${locations}">
		//generate a new LatLng object from the string.
		latLng = new google.maps.LatLng ${location.toString()}
		;
		//Create a new marker at that location
		marker = new google.maps.Marker({
			position : latLng
		});

		//Put the marker on the map.
		marker.setMap(map);
		</c:forEach>
	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>


<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Thank You For Playing</title>
</head>
<body>
    <div class='gameFrame'>
        <div class='gameNote'>Thank you for playing.</div>
        <div class='gameUserInputArea'>
            <div class='gameInstructions'>
				<c:if test="${message != null && !message.isEmpty()}">
					<p>${requestScope.message}</p>
				</c:if>
			</div>        
			<div id="googleMap" style="width: 500px; height: 380px;"></div>                   
        </div>
        <div class='gameNote'><a href="NewGame">Play Again</a></div>
    </div> 
</body>
</html>