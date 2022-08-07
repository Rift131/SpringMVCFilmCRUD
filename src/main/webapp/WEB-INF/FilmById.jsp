<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
 <head>
   <meta charset="UTF-8">
   <title>View</title>
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
						<td>Film Id: ${result.id } Title: ${result.title } Description:${result.description } Year: ${result.releaseYear }
						Rental Duration: ${result.rentalDuration } Rental Rate: ${result.rentalRate }${result.length } Replacement Cost: ${result.replacementCost }
						Rating: ${result.rating } Special Features: ${result.specialFeatures } Actor List:${result.actorList } Language: ${result.language }
						Catagory: ${result.category }</td>
					</tr>
			</table>

		</c:otherwise>

	</c:choose>
<form action="deleteFilm.do" method="POST">
<input type="hidden" value="${result.id}" name="DeleteThisFilm" />
<input type="submit" value="delete"/>
   </form>
<form action="updateFilm.do" method="GET">
<input type="hidden" value="${result.id}" name="data" />
<input type="submit" value="Update"/>
</form>
<a href="Index.html">Back to home</a>
	<p></p>
</body>
</html>