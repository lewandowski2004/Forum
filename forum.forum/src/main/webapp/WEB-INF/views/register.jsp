<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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

<!---------------------------------- END NAVIGATION  ---------------------------------->

<!---------------------------------- DIV WITH FORM REGISTER  ---------------------------------->

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
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="login">Login</a>
							</div>
							<div class="col-xs-6">
								<a href="#" class="active">Register</a>
							</div>
						</div>
						<hr>
						<p style="color: red">${blad}</p>
						<p style="color: green">${succes}</p>

					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
					
					<!------------------- FORM REGISTER  --------------------->
					
								<form:form id="register-form" modelAttribute="user"
									action="register" method="POST" role="form">
									<div class="form-group">
										<label>Imie</label>
										<form:input type="text" name="firstname" id="firstname"
											path="firstname" tabindex="2" class="form-control"
											placeholder="Imie" />
										<form:errors path="firstname" cssClass="help-block" element="em" />
									</div>
									<br />
									<div class="form-group">
										<label>Nazwisko</label>
										<form:input type="text" name="lastname" id="lastname"
											path="lastname" tabindex="2" class="form-control"
											placeholder="Nazwisko" />
										<form:errors path="lastname" cssClass="help-block" element="em" />
									</div>
									<br />
									<div class="form-group">
										<label>Email</label>
										<form:input type="text" name="email" id="email" path="email"
											tabindex="2" class="form-control" placeholder="email" />
										<form:errors path="email" cssClass="help-block" element="em" />
									</div>
									<br />
									<div class="form-group">
										<label>Login</label>
										<form:input type="text" name="login" id="username"
											path="login" tabindex="1" class="form-control"
											placeholder="Username" />
										<form:errors path="login" cssClass="help-block" element="em" />
									</div>
									<br />
									<div class="form-group">
										<label>Hasło</label>
										<form:input type="password" name="password" id="password"
											path="password" tabindex="2" class="form-control"
											placeholder="Hasło" />
										<form:errors path="password" cssClass="help-block" element="em" />
									</div>
									<br />
									<div class="form-group">
										<label>Powtórz hasło</label>
										<form:input type="password" name="confirmPassword"
											id="confirm-password" path="confirmPassword" tabindex="2"
											class="form-control" placeholder="Powtórz hasło" />
										<form:errors path="confirmPassword" cssClass="help-block" element="em" />
									</div>
									<br />
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="register-submit"
													id="register-submit" tabindex="4"
													class="form-control btn btn-register" value="Rejestruj" />
											</div>
										</div>
									</div>
								</form:form>
								
				<!---------------- END FORM REGISTER  --------------------->
				
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<!---------------------------------- DIV WITH FORM REGISTER  ---------------------------------->

	<script src="${js}/jquery-3.2.1.min.js"></script>
	<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
	<script src="${js}/bootstrap.min.js"></script>
	<script src="${js}/bootstrap.min.js"></script>
	<script src="${js}/forum.js"></script>

</body>