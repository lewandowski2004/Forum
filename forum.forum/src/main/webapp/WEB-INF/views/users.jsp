<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

		<div class="container">
			<div class="col-md-12">
				<div class="row">

					<c:if test="${not empty message}">
						<div class="col-xs-12">
							<div class="alert alert-danger alert-dismissible">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								${message}
							</div>
						</div>
					</c:if>

					<div class="col-xs-12">
						<table id="myTable" class="table table-striped table-borderd">
							<thead>
								<tr>
									<th>ID</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Email</th>
									<th>Login</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="user" items="${users}">
									<tr>
										<th>${user.id}</th>
										<th>${user.firstname}</th>
										<th>${user.lastname}</th>
										<th>${user.email}</th>
										<th>${user.login}</th>
										<th><form:form modelAttribute="user" action="users"
												method="POST" role="form">
												<input type="hidden" name="id" id="id" path="id"
													tabindex="2" value="${user.id}" />

												<div class="form-group">
													<div class="row">
														<div class="col-sm-6 col-sm-offset-3">
															<input type="submit" name="action" id="register-submit"
																onclick="return confirm('Czy na pewno chcesz Usunąć ?')"
																tabindex="4" class="form-control btn btn-danger"
																value="Usuń" />
														</div>
														<br /> <br />

													</div>
												</div>
											</form:form></th>
										
									</tr>
								</c:forEach>
							</tbody>
						</table>
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