<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"  import="java.sql.*" 
    import="org.apache.commons.lang3.StringUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>회원목록</title>
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/base.css" rel="stylesheet">
  <script src="js/jquery-1.8.2.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page = "share/header.jsp">
<jsp:param name = "current" value = "show"/>
	</jsp:include>
	<c:if test = "${user.name !=null }">
		<table>
		<c:forEach var="user" items="${users }">
			<tr>
				<td>${user.name }</td>
				<td><img src="https://graph.facebook.com/${user.id }/picture"></td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
	<c:if test = "${user.name ==null }">
	${error }
	<br>
	<a class="brand" href="user?op=login">log in</a>
	</c:if>
</body>
</html>