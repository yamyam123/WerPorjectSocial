<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원목록</title>
<link href="css/stylesheet.css" rel="stylesheet">
	<script src="js/jquery-1.8.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page = "share/header.jsp">
	<jsp:param name = "current" value = "index"/>
	</jsp:include>
<div class = "wrap">
<div class="main">
	현재 페이지는 index 입니다. ${user.id } ${user.name } ${user.birth } ${user.gender }
</div>
</div>
<jsp:include page = "share/footer.jsp" />
</body>
</html>