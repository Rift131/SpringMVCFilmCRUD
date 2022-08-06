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
	Display film:
	<c:choose>

		<c:when test="${empty result }">
			<h2>Film unable to be added.</h2>
		</c:when>

		<c:otherwise>
							Film successfully added!
			<table>
				<tr>
					<td>${result.id }${result.title }${result.description }
						${result.releaseYear } ${result.languageId} ${result.rentalDuration }${result.rentalRate }${result.length }
						${result.replacementCost } ${result.rating }${result.specialFeatures }</td>
				</tr>
			</table>

		</c:otherwise>

	</c:choose>
	<a href="Index.html">Back to home</a>
	<p></p>
</body>
</html>