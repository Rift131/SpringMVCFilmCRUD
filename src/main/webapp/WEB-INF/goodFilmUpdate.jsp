<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
 <head>
   <meta charset="UTF-8">
   <title>FilmUpdateSuccessful</title>
   <style><%@include file="/WEB-INF/jspStyleSheet.css"%></style>
 </head>
<body>
	<a href="Index.html">Back to home</a>
	<h2>Updated Film:</h2>
	<table>
					<tr>
					<td>
					<strong>FILM ID: </strong> ${result.id }
					</td>
					</tr>
					
					<tr>
						<td><strong> FILM TITLE: </strong> ${result.title }  
						<strong> YEAR: </strong>${result.releaseYear }
						<strong> FILM RATING: </strong>${result.rating }  
						<strong> FILM LENGTH: </strong>${result.length } 
						<strong> LANGUAGE: </strong> ${result.language }
						</td> 
					</tr>
					<tr>
					<td>
					<strong> FILM DESCRIPTION: </strong>${result.description }
					</td>
					</tr>
					
					<tr>
					<td>
					<strong> SPECIAL FEATURES: </strong>${result.specialFeatures } 
					</td>
					</tr>
					
					<tr>
					<td>
					<strong>RENTAL DURATION: </strong>${result.rentalDuration } <strong>   RENTAL RATE: </strong>${result.rentalRate } <strong>   REPLACEMENT COST: </strong> ${result.replacementCost }
					</td>
					</tr>
			</table>
<form action="deleteFilm.do" method="POST">
<input type="hidden" value="${result.id}" name="DeleteThisFilm" />
<input type="submit" value="delete" class="delete"/>
   </form>
	<p></p>
</body>
</html>