<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link href="${css}/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${css}/styleForum.css">
<link rel="stylesheet" href="${css}/style.css">

</head>
<body>
	<div class="wrapper">

		<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

			</div>

			<div class="collapse navbar-collapse" id="myNavbar">

				<ul class="nav navbar-nav">
					<li><a href="${contextRoot}/index">Strona Główna</a></li>
					<li><a href="${contextRoot}/newTopic" class="confirm">Nowy
							Temat</a></li>
					<li><a href="#">Page 2</a></li>
				</ul>

			</div>
		</div>
		</nav>
		<div class="container">
			<div class="row">

				<c:if test="${not empty message}">
					<div class="col-xs-12">
						<div class="alert alert-success alert-dismissible">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							${message}
						</div>
					</div>
				</c:if>

				<div class="col-md-6 col-md-offset-3">
					<div class="panel panel-login">
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-12">
									<br />
									<h3 style="text-align: center">Dodawanie Podkategorii</h3>
									<hr>
									<p style="color: red">${blad}</p>
									<form:form id="register-form" modelAttribute="subcategory"
										action="subcategory" method="POST" role="form">
										<div class="form-group">
											<label>Wybierz Kategorie</label>
												<select name="cat" id="inputState" class="form-control">
													<c:forEach var="category" items="${categories}">
														<option value="${category.id}">${category.name}</option>
													</c:forEach>
												</select>
											<label>Nazwa Podkategorii</label>
											<input type="text" name="name" id="name" 
												tabindex="2" class="form-control" value="" />
										</div>
										<br />


										<div class="form-group">
											<div class="row">
												<div class="col-sm-6 col-sm-offset-3">
													<input type="submit" name="action" id="register-submit"
														tabindex="4" class="form-control btn btn-success"
														onclick="return confirm('Czy na pewno chcesz dodać ?')"
														value="Dodaj Podkategorie" />
												</div>
												<br /> <br />

											</div>
										</div>
									</form:form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!---------------------------------- FOOTER -------------------------------------->
		<footer>

		<p>© 2017 </p>

		</footer>
		<!-------------------------------- END FOOTER ------------------------------------>

	</div>


	<script src="${js}/bootstrap.min.js"></script>
	<script src="${js}/jquery-3.2.1.min.js"></script>
	<script src="${js}/forum.js"></script>
	<script src="${js}/tooltip.js"></script>
	<script src="${js}/confirmation.js"></script>




</body>
</html>