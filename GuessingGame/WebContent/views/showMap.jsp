<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


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
		latLng = new google.maps.LatLng$
		{
			location
		}
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
<title>Insert title here</title>
</head>
<body>

	<div id="googleMap" style="width: 500px; height: 380px;"></div>

	<!-- - please feel free to add text... In fact, please make this sound cool -->

</body>
</html>
