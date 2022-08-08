<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
 <head>
   <meta charset="UTF-8">
   <title>UpdateFilm</title>
         <style><%@include file="/WEB-INF/jspStyleSheet.css"%></style>
   
 </head>
 <body>
 
 
 
 <a href="Index.html">Back to home</a>
	<h2>Update Film:</h2>
	<table>
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
			</table>
			
	<form action="updateFilmForReal.do" method="POST">
	
			Title: <input type="text" name="title" /> ${result.title }<br />  
			Description: <input type="text" name="description" /> ${result.description }<br />  
			Release Year: <input type="text" name="releaseYear" /> ${result.releaseYear }<br /> 
			Rental Duration: <input type="text" name="rentalDuration" /> ${result.rentalDuration }<br /> 
			Rental Rate: <input type="text" name="rentalRate" /> ${result.rentalRate }<br />  
			Film Length: <input type="text" name="length" /> ${result.length }<br /> 
			Replacement Cost: <input type="text" name="replacementCost" /></input> ${result.replacementCost } <br /> 
			
			
			Current Film Rating: ${result.rating } <br>
			<input type="radio" id="G" name= "rating" value="G"/>G
			<input type="radio" id="PG" name= "rating" value="PG" />PG
			<input type="radio" id="PG13" name= "rating" value="PG13" />PG-13 
			<input type="radio" id="R" name= "rating" value="R" />R
			<input type="radio" id="NC17" name= "rating" value="NC17"/>NC-17<br/>
			
			Current Special Features: ${result.specialFeatures } <br>
			<input type="checkbox" id="Trailers" name= "specialfeatures" value="Trailers" />Trailers 
			<input type="checkbox" id="Commentaries" name= "specialfeatures" value="Commentaries" />Commentaries
			<input type="checkbox" id="Deleted Scenes" name= "specialfeatures" value="Deleted Scenes" />Deleted Scenes
			<input type="checkbox" id="Behind the Scenes" name= "specialfeatures" value="Behind the Scenes" />Behind the Scenes  
			
			<br></br>
			
		<input type="submit" value="Submit" /><br />

	</form>
 
 
 
 
 
 </body>
</html>