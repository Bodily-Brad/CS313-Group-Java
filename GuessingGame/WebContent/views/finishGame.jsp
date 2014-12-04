<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/includes/_head.html"/>
<body>
    <div class='gameFrame'>
        <div class='gameNote'>Thank you for playing.</div>
        <div class='gameUserInputArea'>
            <div class='gameInstructions'>
				<c:if test="${message != null && !message.isEmpty()}">
					<p>${requestScope.message}</p>
				</c:if>                         
                <p>
                    Every time you play, it helps me learn a little more and get
                    a little better at guessing your secret object. If you'd like
                    to go again, click 'Play Again' below.
                </p>
            </div>           
        </div>
        <div class='gameNote'><a href="NewGame">Play Again</a></div>
    </div> 
</body>
</html>