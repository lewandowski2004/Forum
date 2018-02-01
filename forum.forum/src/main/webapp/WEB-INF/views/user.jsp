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
</head>

<body>

<!---------------------------------- NAVIGATION  ---------------------------------->

	
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
			</ul>


			
		</div>
		<!-- /.navbar-collapse -->
	</div>
</nav>

		<div class="container">
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
					<div class="panel panel-login">
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-12">
									<h3 style="text-align: center">Usuwanie, Aktualizacja
										Użytkownika</h3>
									<hr>
									<p style="color: red">${blad}</p>
									<form:form id="register-form" modelAttribute="user"
										action="user" method="POST" role="form">
										<div class="form-group">
											<form:input type="hidden" name="id" id="id" path="id"
												tabindex="2" class="form-control" placeholder="Id"
												value="${user.id}" />
										</div>
										<br />
										<div class="form-group">
											<label>First Name</label>
											<form:input type="text" name="firstname" id="firstname"
												path="firstname" tabindex="2" class="form-control"
												placeholder="firstname" value="${user.firstname}" />
											<form:errors path="firstname" cssClass="help-block"
												element="em" />
										</div>
										<br />
										<div class="form-group">
											<label>Last Name</label>
											<form:input type="text" name="lastname" id="lastname"
												path="lastname" tabindex="2" class="form-control"
												placeholder="lastname" value="${user.lastname}" />
											<form:errors path="lastname" cssClass="help-block"
												element="em" />
										</div>
										<br />
										<div class="form-group">
											<label>Email</label>
											<form:input type="text" name="email" id="email" path="email"
												tabindex="2" class="form-control" placeholder="email"
												value="${user.email}" />
											<form:errors path="email" cssClass="help-block" element="em" />
										</div>
										<br />
										<div class="form-group">
											<label>Login</label>
											<form:input type="text" name="login" id="username"
												path="login" tabindex="1" class="form-control"
												placeholder="login" value="${user.login}" />
											<form:errors path="login" cssClass="help-block" element="em" />
										</div>
										<br />
										<div class="form-group">
											<%-- <label>Rola</label>
										<form:input type="text" name="role" id="username" path="role"
											tabindex="1" class="form-control" placeholder="Username"
											value="${user.role}" /> 
										<label>Rola: ${user.role}</label> --%>
											<label for="inputState" class="col-form-label">Rola
												Użytkownika</label>
										<select name="role" id="inputState"
												class="form-control">
												
												<%-- <c:forEach var="role" items="${roles}"> --%>
													<option value="1">ADMIN</option>
													<option value="2">USER</option>
												<%-- </c:forEach> --%>

											</select>
										</div>

										<br />
										<div class="form-group">

											<form:input type="hidden" name="password" id="haslo"
												path="password" tabindex="2" class="form-control"
												placeholder="lastname" value="${user.password}" />
										</div>
										<br />


										<div class="form-group">
											<div class="row">
												<div class="col-sm-6 col-sm-offset-3">
													<input type="submit" name="action" id="register-submit"
														onclick="return confirm('Czy na pewno chcesz Zaktualizować ?')"
														tabindex="4" class="form-control btn btn-success"
														value="Zaktualizuj" />
												</div>
												<br /> <br />
												<div class="col-sm-6 col-sm-offset-3">
													<input type="submit" name="action" id="register-submit"
														onclick="return confirm('Czy na pewno chcesz Usunąć ?')"
														tabindex="4" class="form-control btn btn-danger"
														value="Delete" />
												</div>

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

		<p>© 2017</p>

		</footer>
		<!-------------------------------- END FOOTER ------------------------------------>

	</div>

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