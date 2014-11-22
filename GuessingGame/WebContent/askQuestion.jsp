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
		<div class='gameNote'>Questions Left: ${param.NumQuestionsLeft}</div>
		<div class='gameUserInputArea'>
			<div class='gameQuestion'>${param.QuestionText}</div>
			<div class='gameButtonArea'>
				<c:forEach var="answer" items="${answers}">
					<form class='gameUserInput' method='post' action='Play'>
						<input type='hidden' name='action' value='answerQuestion'>
						<input type='hidden' name='answerID' value='${param.answerID}'>
						<input type='hidden' name='questionID' value='${param.questionID }'>
						<input type='submit' value='${answer.GetText()}'>
					</form>
				</c:forEach>
				<br>				
			</div>
		</div>
		<div class='gameNote'><a href="?action=End">Start Over</a></div>
	</div>
</body>
</html>