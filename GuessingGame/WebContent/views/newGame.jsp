<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/includes/_head.html"/>
<body>
    <div class='gameFrame'>
        <div class='gameNote'>How to Play</div>
        <div class='gameUserInputArea'>
            <div class='gameInstructions'>
                <p>
                    You pick a secret object, then I'll ask a series of "yes/no" type questions.
                    Based on your answers, I'll guess which item you're thinking of.
                    Try to pick that answer that you think is most accurate, but
                    don't put too much thought into it - if nothing seems appropriate,
                    feel free to say "I Don't Know".
                </p>             
                <p>
                    I'm still very new at this and so I have a lot of learning to do.
                    For now, please pick something from the list below as your secret object.
                </p>
            </div>
            <div class='gameItemList'>
                <ul>
					<c:forEach var="item" items="${items}">
						<li>${item.description }</li>
					</c:forEach>                                    
                </ul>
            </div>
            <div class='gameInstructions'>
                <p>
                    Once you've picked your secret item, click the link below to get
                    started.
                </p>         
            </div>            
        </div>
        <div class='gameNote'><a href="Play">Okay, got it. Let's go!</a></div>
    </div>
</body>
</html>