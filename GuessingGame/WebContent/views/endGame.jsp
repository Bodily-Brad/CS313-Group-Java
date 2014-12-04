<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/includes/_head.html"/>
<body>
    <div class='gameFrame'>
        <div class='gameNote'>Were you thinking of...</div>
        <div class='gameUserInputArea'>
            <div class='gameQuestion'>${guessItem.description }</div>
            <div class='gameButtonArea'>
                <form class='gameUserInput' method='post' action='ConfirmGuess'>
                    <input type='hidden' name='action' value='confirmGuess'>
                    <input type='hidden' name='itemID' value='${guessItem.ID }'>
                    <input type='submit' value='Yes'>
                </form>
                <form class='gameUserInput' method='post' action='DenyGuess'>
                    <input type='hidden' name='action' value='denyGuess'>
                    <input type='hidden' name='itemID' value='${guessItem.ID }'>
                    <input type='submit' value='No'>
                </form>                
                <br>
            </div>
        </div>
        <div class='gameNote'><a href="NewGame">Start Over</a></div>
    </div> 
</body>
</html>