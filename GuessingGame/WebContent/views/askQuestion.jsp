<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/includes/_head.html"/>
<body>
	<div class='gameFrame'>
		<div class='gameNote'>Questions Left: ${NumQuestionsLeft}</div>
		<div class='gameUserInputArea'>
			<div class='gameQuestion'>${question.text}</div>
			<div class='gameButtonArea'>
				<c:forEach var="answer" items="${answers}">
					<form class='gameUserInput' method='post' action='Play'>
						<input type='hidden' name='action' value='answerQuestion'>
						<input type='hidden' name='answerID' value='${answer.ID}'>
						<input type='hidden' name='questionID' value='${question.ID }'>
						<input type='submit' value="${answer.text}">
					</form>
				</c:forEach>
				<br>				
			</div>
		</div>
		<div class='gameNote'><a href="NewGame">Start Over</a></div>
	</div>
</body>
</html>