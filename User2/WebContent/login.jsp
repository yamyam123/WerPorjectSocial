<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link href="css/stylesheet.css" rel="stylesheet">
	
	<script src="js/jquery-1.8.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="share/header.jsp">
  <jsp:param name="current" value="log in"/>
</jsp:include>
<c:if test="${log == 'login'}">
<form class="form-horizontal" action="user" method="POST">
	<label class="control-label" for="userid">ID</label>
	<input type="text" name="userid"><br>
	<label class="control-label" for="pwd">Password</label>
	<input type="password" name="pwd"><br>
	<div class="form-actions">
	<input type ="hidden" name="type" value="log">
	<input type="submit" class="btn btn-primary" value="log in">
	<input type="hidden" name="log" value="login">
	</div>
</form>
</c:if>
<c:if test="${log == 'logout' }">

<form class="form-horizontal" action="user" method="POST">
<input type="hidden" name="userid" value="${user.userid }">
<input type="hidden" name="pwd" value="${user.pwd }">
<input type="hidden" name="log" value="PUT">
<input type ="hidden" name="type" value="log">
<input type="submit" class="btn btn-primary" value="log out">
</form>
${user.name }님 환영합니다.
</c:if>
</body>
</html>