<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script
	src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCrkJAZZAeEnD90ThXYsfaEgbjW0NQ6XTQ&amp;sensor=true"
	type="text/javascript">
	
</script>

<script type="text/javascript">
function initialize()
{
var mapProp = {
  center:new google.maps.LatLng(43.8,-111.8),
  zoom:5,
  mapTypeId:google.maps.MapTypeId.ROADMAP
  };
var map=new google.maps.Map(document.getElementById("googleMap")
  ,mapProp);
  
<c:forEach var="location" items="${locations}">
latLng = new google.maps.LatLng${location};
marker=new google.maps.Marker({
	  position:latLng
	  });

	marker.setMap(map);
</c:forEach>
}

google.maps.event.addDomListener(window, 'load', initialize);
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Previous Visitor Locations</title>
</head>
<body>

	<div id="googleMap" style="width: 500px; height: 380px;"></div>

	<div>
		Saved Locations:
		<c:forEach var="location" items="${locations}">
			<strong>${location}</strong>
			<br />
		</c:forEach>
	</div>

</body>
</html>