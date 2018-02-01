<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="img" value="/resources/img" />
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<title>Insert title here</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<link rel="stylesheet" href="${css}/styleForums.css">
<link rel="stylesheet" href="${css}/MainCss.css">
<%-- <link rel="stylesheet" href="${css}/styleForum.css">
	<link rel="stylesheet" href="${css}/style.css"> --%>
</head>

<body>

	<!---------------------------------------- IMG  ---------------------------------------->
	<div class="row">
		<img src="${img}/tapeta.png" />
	</div>

	<!---------------------------------------- NAVIGATION  ---------------------------------------->

	<nav class="navbar navbar-default">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Brand</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">

			<ul class="nav navbar-nav">
				<li><a href="${contextRoot}/index">Strona Główna</a></li>
				<li><a href="${contextRoot}/newTopic">Nowy Temat</a></li>
				<li><a href="#">Page 2</a></li>

				<security:authorize access="hasAuthority('USER')">
					<li><a href="${contextRoot}/message">Wiadomość do Admina</a></li>
				</security:authorize>
				<security:authorize access="hasAuthority('ADMIN')">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"> Panel Zarządzania<span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="${contextRoot}/panel/users">Uzytkownicy</a></li>
							<li><a href="${contextRoot}/panel/topics">Tematy</a></li>
							<li><a href="${contextRoot}/panel/category">Kategorie</a></li>
							<li><a href="${contextRoot}/panel/subcategory">Podkategorie</a></li>
							<li><a href="${contextRoot}/panel/messages">Wiadomości</a></li>
						</ul></li>
				</security:authorize>

			</ul>


			<ul class="nav navbar-nav navbar-right">
				<security:authorize access="isAnonymous()">
					<li><a href="${contextRoot}/register"><span
							class="glyphicon glyphicon-user"></span>Rejestracja</a></li>
					<li><a href="${contextRoot}/login"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>
				</security:authorize>

				<security:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="javascript:void(0)"
						class="dropdown-toggle" data-toggle="dropdown" id="dropdownMenu1"
						role="button" aria-haspopup="true" aria-expanded="false">
						<img style="max-height:32px;max-width:32px" class="img-circle" src="${img}/user.png"
													alt="zdjęcie" width="64">${userModel.login} <span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="${contextRoot}/perform-logout">Wyloguj</a></li>
						</ul></li>
				</security:authorize>


			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<!---------------------------------------- END NAVIGATION ---------------------------------------->

	<!-------------------------------- CONTAINER WITH TOPIC and CATEGORIES ---------------------------------->



	<div class="container">
		<!------------------------------------------------ DIV WITH TOPIC --------------------------------------------------->
		
		<!------------------------------------------------ END DIV WITH CATEGORIES -------------------------------------------->
		<div class="row">
			<div class="col-md-8">
				<div class="panel panel-warning">
					<div class="panel-heading">
						<h3>Tematy</h3>
					</div>
				</div>
				<table id="myTable">
					<thead>
						<tr>
							<th></th>

						</tr>
					</thead>

					<tbody>

						<!---------------------- LOOP WITH TOPIC ------------------->
						<c:forEach var="topic" items="${topics}">
							<tr>
								<th>
									<div class=" panel panel-default" style="padding: 20px">
										
										<div class="media">

											<div class="media-left" style="padding-right: 20px">
												<img id="media-object" class="img-circle" src="${img}/user.png"
													alt="zdjęcie" width="64">
												<p style="text-align: center"> ${topic.user.login} </p>
												
											</div>
											<div class="media-body">

												<h4 class="media-heading">
													<c:url var="url" scope="page" value="/topic">
														<c:param name="id" value="${topic.id}" />
													</c:url>
													<a style="font-size: 30px" title="Czytaj" href="${url}">
														${topic.title} </a><br /><br />
														<small><i> <fmt:formatDate
																	type="date" value="${topic.date}" /></i></small>
												</h4>
												<hr />
												<p style="max-width: 600px; max-height: 95px; font-style: oblique; color:#adabab; font-weight: 300">${topic.content}</p>

											</div>
										</div>
										

									</div>
								</th>
							</tr>
						</c:forEach>
						<!-------------------- END LOOP WITH TOPIC ------------------------>


					</tbody>

				</table>
			</div>
			<div class="col-md-4">
				<div class="list-group">
					<div class="panel panel-warning">
						<div class="panel-heading">
							<h3>Kategorie</h3>
						</div>
					</div>

					
					<!-------------------- LOOP WITH CATEGORIES ------------------->
					
					<c:forEach var="category" items="${categories}">
						<div class="panel panel-info">
							<div class="panel-heading">
								<h5>${category.name}</h5>
								<hr />
								
								<!------------------ LOOP WITH SUBCATEGORIES ----------------->
								
								<c:forEach var="subcategory" items="${category.subcategories}">
									<c:url var="url" scope="page" value="/TopicInCategory">
										<c:param name="id" value="${subcategory.id}" />
									</c:url>
									<a style="padding-left: 30px" href="${url}">
										${subcategory.name} </a>
										<br />
								</c:forEach>
								<!------------------ END LOOP WITH SUBCATEGORIES ----------------->
							</div>
						</div>

					<!------------------ END LOOP WITH CATEGORIES ----------------->


					</c:forEach>
				</div>

			</div>

		</div>
	</div>

		<!---------------------------------- FOOTER -------------------------------------->
		<footer>

		<p>© 2017</p>

		</footer>
		<!-------------------------------- END FOOTER ------------------------------------>



		<script src="${js}/jquery.js"></script>
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
			integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
			crossorigin="anonymous"></script>
		<%-- <script src="${js}/bootstrap.js"></script> --%>
		<script src="${js}/jquery.dataTables.js"></script>
		<script src="${js}/forum.js"></script>
		<script src="${js}/dataTables.bootstrap.js"></script>
</body>
</html>