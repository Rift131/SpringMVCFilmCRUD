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
	Display film:
	<c:choose>

		<c:when test="${empty result }">
			<h2>No film found</h2>
		</c:when>

		<c:otherwise>

			<table>
					<tr>
						<td>${result.id }${result.title }${result.description } ${result.releaseYear }
						${result.rentalDuration }${result.rentalRate }${result.length } ${result.replacementCost }
						${result.rating }${result.specialFeatures }${result.actorList } ${result.language }
						${result.category }</td>
					</tr>
			</table>

		</c:otherwise>

	</c:choose>
<form action="deleteFilm.do" method="POST">
<input type="hidden" value="${result.id}" name="DeleteThisFilm" />
<input type="submit" value="delete"/>
   </form>
<a href="Index.html">Back to home</a>
	<p></p>
</body>
</html>