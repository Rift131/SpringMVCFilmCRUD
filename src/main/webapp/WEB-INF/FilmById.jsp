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

		<c:when test="${empty filmById }">
			<h2>No film found</h2>
		</c:when>

		<c:otherwise>

			<table>
					<tr>
						<td>${filmById.id }${filmById.title }${filmById.description } ${filmById.releaseYear }
						${filmById.rentalDuration }${filmById.rentalRate }${filmById.length } ${filmById.replacementCost }
						${filmById.rating }${filmById.specialFeatures }${filmById.actorList } ${filmById.language }
						${filmById.catagory }</td>
					</tr>
			</table>

		</c:otherwise>

	</c:choose>

	<p></p>
</body>
</html>