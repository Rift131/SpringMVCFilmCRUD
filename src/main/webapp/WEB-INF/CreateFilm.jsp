<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add a Film</title>
</head>
<body>
	<a href="Index.html">Back to home</a>
	<br></br>
	<h1>Your Added Film</h1>
	<br></br>
	<c:choose>

		<c:when test="${empty result }">
			<h2>Film unable to be added.</h2>
		</c:when>

		<c:otherwise>
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
					<strong> RENTAL DURATION: </strong>${result.rentalDuration } <strong> RENTAL RATE: </strong>${result.rentalRate } <strong> REPLACEMENT COST: </strong> ${result.replacementCost }
					</td>
					</tr>
			</table>

		</c:otherwise>

	</c:choose>
	<p></p>
</body>
</html>

