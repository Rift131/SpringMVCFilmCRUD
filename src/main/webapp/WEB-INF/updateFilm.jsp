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
 
 
 
 <a href="Index.html">Back to home</a>
	<h2>Update Film:</h2>
	<table>
					<tr>
						<td>${result.id }${result.title }${result.description } ${result.releaseYear }
						${result.rentalDuration }${result.rentalRate }${result.length } ${result.replacementCost }
						${result.rating }${result.specialFeatures }${result.actorList } ${result.language }
						${result.category }</td>
					</tr>
			</table>
			
	<form action="updateFilmForReal.do" method="POST">
	
			Title: <input type="text" name="title" /> ${result.title }<br />  
			Description: <input type="text" name="description" />${result.description }<br />  
			Release Year: <input type="text" name="releaseYear" />${result.releaseYear }<br /> 
			Rental Duration: <input type="text" name="rentalDuration" />${result.rentalDuration }<br /> 
			Rental Rate: <input type="text" name="rentalRate" />${result.rentalRate }<br />  
			Film Length: <input type="text" name="length" />${result.length }<br /> 
			Replacement Cost: <input type="text" name="replacementCost" /></input>${result.replacementCost } <br /> 
			
			
			Current Film Rating:${result.rating }
			<input type="radio" id="G" name= "rating" value="G"/>G
			<input type="radio" id="PG" name= "rating" value="PG" />PG
			<input type="radio" id="PG13" name= "rating" value="PG13" />PG-13 
			<input type="radio" id="R" name= "rating" value="R" />R
			<input type="radio" id="NC-17" name= "rating" value="NC-17"/>NC-17<br/>
			
			Special Features: ${result.specialFeatures }
			<input type="checkbox" id="Trailers" name= "specialfeatures" value="Trailers" />Trailers 
			<input type="checkbox" id="Commentaries" name= "specialfeatures" value="Commentaries" />Commentaries
			<input type="checkbox" id="Deleted Scenes" name= "specialfeatures" value="Deleted Scenes" />Deleted Scenes
			<input type="checkbox" id="Behind the Scenes" name= "specialfeatures" value="Behind the Scenes" />Behind the Scenes  
			
			<br></br>
			
		<input type="submit" value="Submit" /><br />

	</form>
 
 
 
 
 
 </body>
</html>