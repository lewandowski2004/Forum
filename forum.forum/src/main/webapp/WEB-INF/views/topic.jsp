<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="img" value="/resources/img" />

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
		

		<!---------------------------------- END NAVIGATION ---------------------------------->

		

			<!---------------------------------- DIV WITH TOPIC (title, content,user,date)  ---------------------------------->

			
		<!---------------------------------- END DIV WITH TOPIC (title, content,user,date)  ---------------------------------->

		<!---------------------------------- DIV WITH TOPIC.ENTRY (user,date,content)  ---------------------------------->


		

		<div class="col-md-12">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h2>Temat: ${topic.title}</h2>
				</div>
			</div>
			<br />
			<div class="row">
				<div class="col-md-4 col-sm-4 col-xs-12 ">
					<p style="text-align: center; font-size: 18px">
						<span style="padding-right: 20px" class="glyphicon glyphicon-user"
							aria-hidden="true"></span>${topic.user.login}
					</p>
				</div>
				<div class="col-md-4 col-sm-4 col-xs-12 ">
					<p style="text-align: center; font-size: 18px">
						<span style="padding-right: 20px"
							class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
						<fmt:formatDate type="date" value="${topic.date}" />
					</p>
				</div>
				<div class="col-md-4 col-sm-4 col-xs-12 ">
					<p style="text-align: center; font-size: 18px">
						<span style="padding-right: 20px"
							class="glyphicon glyphicon-wrench" aria-hidden="true"></span>${topic.category.name}
					</p>
				</div>
			</div>
			<hr />
			<div style="col-md-12">
				<p>${topic.content}</p>
				<hr />
				<br />
			</div>
		
		<div class="panel panel-info">
				<div class="panel-heading">
					<h3>Komentarze:</h3>
				</div>
		</div>
		
		
			
		<c:forEach var="entry" items="${topic.entries}">

			<div class="media"
				style="padding: 5px; border: 1.5px solid #ddd;; border-radius: 5px">
				<div class="media-left" style="padding-right: 20px">
					<img class="media-object img-circle" src="${img}/user.png"
						width="64">
				</div>
				<div class="media-body">
					<h4 class="media-heading">${entry.user.login}</h4>
					<a> <small><i><fmt:formatDate type="both"
									dateStyle="medium" timeStyle="medium" value="${entry.date}" /></i></small></a><br />
					<p>${entry.content}</p>

					<c:forEach var="secondaryEntry" items="${entry.secondaryEntries}">

						<div class="media">
							<div class="media-left" style="padding-right: 20px">
								<img class="media-object img-circle" src="${img}/user.png"
									width="64">
							</div>
							<div class="media-body  ">
								<h4 class="media-heading">${secondaryEntry.user.login}</h4>
								<a> <small><i><fmt:formatDate type="both"
												dateStyle="medium" timeStyle="medium"
												value="${secondaryEntry.date}" /></i></small></a><br />
								<p>${secondaryEntry.content}</p>
							</div>

						</div>
						<hr />

					</c:forEach>
					<a role="button" data-toggle="collapse" data-parent="#accordion"
						href="#${entry.id}" aria-expanded="true"
						aria-controls="collapseOne"> Odpowiedz </a>
					<div id="${entry.id}" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="content-form">
							<form:form id="secondaryEntry" method="POST"
								modelAttribute="secondaryEntry" action="secondaryEntry">

								<input type="hidden" name="idd" value="${entry.id}" />
								<input type="hidden" name="idTopic" value="${topic.id}" />
								<div class="form-group" style="margin: 15px">
									<textarea id="tresc" name="content" rows="2" cols="40"
										tabindex="2" class="form-control" placeholder="Treść"></textarea>
								</div>
								<div class="form-group">
									<div class="row">
										<br /> <br />
										<div class="col-sm-6 col-sm-offset-3">
											<input type="submit" name="action" id="register-submit"
												tabindex="4" class="form-control btn btn-info"
												value="Dodaj Odpowiedz" />
										</div>
									</div>
								</div>

							</form:form>
						</div>
					</div>
				</div>

			</div>
		</c:forEach>
		<hr />
	</div>
		
		<!---------------------------------- END DIV WITH TOPIC.ENTRY (user,date,content)  ---------------------------------->

		<!------------------------------------------ DIV WITH FORM FOR CONTENT  ---------------------------------------->

		<div class="col-md-12 col-xs-12" style="float: right">
			<div class="content-form">
				<div class="row">
					<div class="content-form-header">
						<h3>Dodaj komentarz</h3>
					</div>

					<!------------------ FORM CONTENT ---------------------->

					<form:form id="entry" method="POST" modelAttribute="entry"
						action="entry">

						<input type="hidden" name="idd" value="${topic.id}" />
						<div class="form-group" style="margin: 15px">
							<textarea id="tresc" name="content" rows="12" cols="40"
								tabindex="2" class="form-control" placeholder="Treść"></textarea>
						</div>
						<div class="form-group">
							<div class="row">
								<br /> <br />
								<div class="col-sm-6 col-sm-offset-3">
									<input type="submit" name="action" id="register-submit"
										tabindex="4" class="form-control btn btn-info"
										value="Dodaj Komentarz" />
								</div>
							</div>
						</div>

					</form:form>

					<!------------------ FORM CONTENT ---------------------->

				</div>
			</div>
		</div>

		<!------------------------------------------ END DIV WITH FORM FOR CONTENT  ---------------------------------------->
	</div>

	<!---------------------------------- FOOTER -------------------------------------->
	<footer>

	<p>© 2017</p>

	</footer>
	<!-------------------------------- END FOOTER ------------------------------------>

	</div>

	<script src="${js}/jquery-3.2.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>


</body>
</html>