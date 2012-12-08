<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
 <script src="js/jquery-1.8.2.min.js"></script>
 <script src="js/bootstrap.min.js"></script>
 <script src="http://code.jquery.com/jquery-latest.js"></script>
<div class ="wrap">
<div class ="main">
<c:if test ="${problem.id !=null}">
 문제 : ${problem.title}<br>
 <form method="POST">
 <c:if test ="${problem.pNumber ==2}">
 <input type="radio" name="answer" value="1" checked="checked">${problem.first}
 <input type="radio" name="answer" value="2">${problem.second}
 </c:if>
  <c:if test ="${problem.pNumber ==3}">
  <input type="radio" name="answer" value="1" checked="checked">${problem.first}
  <input type="radio" name="answer" value="2">${problem.second}
  <input type="radio" name="answer" value="3">${problem.third}
 </c:if>
  <c:if test ="${problem.pNumber ==4}">
  <input type="radio" name="answer" value="1" checked="checked">${problem.first}
  <input type="radio" name="answer" value="2">${problem.second}
  <input type="radio" name="answer" value="3">${problem.third}
  <input type="radio" name="answer" value="4">${problem.fourth}
 </c:if>
 <br>
 <input type ="hidden" name="type" value="answer">
 <input type="submit" value="풀기">
 </form>
 </c:if>
 <c:if test ="${problem.id ==null}">
	상대가 문제를 입력하지 않았습니다. 상대가 문제를 입력할때까지 기다려 주세요.<br>
	<a href="user?op=rshow&id=${user.id  }">뒤로가기</a>
 </c:if>
 </div>
 </div>
</body>
</html>