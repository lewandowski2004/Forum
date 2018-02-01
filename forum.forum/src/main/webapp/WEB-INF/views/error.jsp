<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="${css}/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${css}/styleforum.css">
	<link rel="stylesheet" href="${css}/style.css">
</head>

<body>
	<div class="content">
		<div class="container">
			<div class="row">
				<div class="col-xs-12">
					<div class="jumbotron">
						<h1>${errorTitle}</h1>
						<hr/>
						
						<blockquote>
							${errorDescription}
						</blockquote>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${js}/bootstrap.min.js"></script>
	<script src="${js}/jquery-3.2.1.min.js"></script>

</body>

</html>