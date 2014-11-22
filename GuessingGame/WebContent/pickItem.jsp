<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Guessing Game</title>
</head>
<body>
    <div class='gameFrame'>
        <div class='gameNote'>You Win</div>
        <div class='gameUserInputArea'>
            <div class='gameInstructions'>
				<c:if test="${message != null && !message.isEmpty()}">
					<p>${requestScope.message}</p>
				</c:if>           
            </div>
            <div class='gameItemList'>
                <form class='gameUserInput' method='post' action='/game/play/'>
                    <input type='hidden' name='action' value='provideCorrectItem'>
                    <ul>
						<c:forEach var="item" items="${items}">
							<li>
								${item.description }
								<input type='radio' name='itemID' value='${item.itemID }'>${item.description}
							</li>
						</c:forEach>                  
                    </ul>                    
                    <br>
                    <input type='submit' value='Submit'>
                </form>
            </div>            
        </div>
        <div class='gameNote'><a href="?action=End">Start Over</a></div>
    </div> 
</body>
</html>