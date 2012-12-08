<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/stylesheet.css" rel="stylesheet">
</head>
<body>
<jsp:include page="share/header.jsp">
  <jsp:param name="current" value="null"/>
  </jsp:include>
<div class ="wrap">
<div class ="main">
<c:if test = "${answer == 4}">
${msg }
<br>상대가 입력한 핸드폰 번호는
<c:out value="${secret }"></c:out> 입니다.
</c:if>
<c:if test = "${answer == 3}">
${msg }
</c:if>
<br><a href="user?op=rshow&id=${user.id}">뒤로가기</a>
</div>
</div>
</body>
</html>