<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" type="text/css" href="style/bcb.css" media="screen">
  <title>CS 313 JSP Guessing Game Details</title>
</head>
<body>
<article class="appStoreEntry">
	<h2>Guessing Game</h2>
	URL: <a href="NewGame">Play the Guessing Game</a>
	<figure class="appScreenShot">
		<img src="media/screenshot01.png" alt="Screen Shot of Guessing Game">
		<figcaption>Will it guess right?</figcaption>
	</figure>
	<section class="appDescription">
		<h3>Description</h3>
		<p>You've got 20 questions to stump the computer. Pick a secret item then answer the computer's questions as it tries to figured out what you're thinking of!</p>
	</section>
	<section class="appDescription">
		<h3>Features</h3>
		<p>The game learns each time you play. It uses your answers to help it play better each and every time a game is played. Whether it guesses your
		item correctly, or it makes a mistake, the game will adapt to try and do better the next time.</p>
		<p>At the end of each game, it will optionally save the location where the game was played from, so you can see where the game has been played.
		(You can <a href="ShowLocations">view the map here</a>).</p>
	</section>
	<section class="appDescription">
		<h3>How To Play</h3>
		<p>Playing is simple: pick a secret item, answer yes/no questions, then see how the computer does! You can <a href="NewGame">click here to play</a>.</p>
		<p>Note: After answering the final question, there is a delay while the game attempts to guess your item. Please be patient, it's working...</p>
	</section>	
	<section class="appDescription">
		<h3>Future Plans</h3>
		<p>The game can support a massive number of potential items, but it takes time for the game to learn about each one. For now, a simple list of items is presented to
		the player before each game, with instructions to select an item from the list. Ultimately, the intention is for the game to allow players to pick any arbitrary object
		they wish, completely at random and without a predefined list. Some changes will need to be made to support this feature, namely: allowing players to tell the game
		what their object was when the game guesses incorrectly.</p>
		<p>
		The game needs to have a better indication that it's working, so it doesn't appear to have hung or to not be doing anything. This would also be a future feature.
		</p>
	</section>
</article>
</body>
</html>