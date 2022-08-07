<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
 <head>
   <meta charset="UTF-8">
   <title>View</title>
         <link rel="stylesheet" href="jspStyleSheet.css">
   
 </head>
<body>
<a href="Index.html">Back to home</a> <br>
 <br></br>
	<h1>All films matching your query...</h1><br></br>
	
	<c:choose>

		<c:when test="${empty results }">
			<h2>No film found</h2>
		</c:when>

		<c:otherwise>
			<table>
		<c:forEach var="result" items="${results}">
						<tr>
					<td>
					<strong>FILM ID: </strong> ${result.id }
					</td>
					</tr>
					
					<tr>
						<td><strong> FILM TITLE: </strong> ${result.title }  
						<strong> YEAR: </strong>${result.releaseYear }
						<strong> FILM CATEGORY: </strong> ${result.category }
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
					<strong> FILM ACTORS: </strong>${result.actors }
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
					
					<tr><td></td></tr>					
					<tr><td></td></tr>					
					<tr><td></td></tr>						
	</c:forEach>
			</table>
		</c:otherwise>

	</c:choose>
	<p></p>
</body>
</html>
