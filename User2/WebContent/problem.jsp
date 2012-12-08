<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link href="css/stylesheet.css" rel="stylesheet">
	<script src="js/jquery-1.8.2.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="http://code.jquery.com/jquery-latest.js"></script>
 	<script src="js/problem.js"></script>
</head>
<body>
<jsp:include page="share/header.jsp">
  <jsp:param name="current" value="null"/>
  </jsp:include>
<div class ="wrap">
<div class ="main">
	<form method="POST">
	핸드폰 번호<input type="text" name="hpnumber" style="ime-mode:disabled;" onkeydown="return onlyNumber(event)"><br>
	
	문제 갯수를 선택하세요<select class="select" onchange="select();">
	<option>선택</option>
	<option>2</option>
	<option>3</option>
	<option>4</option>
	</select>
	<div class="space"></div>
	<input type ="hidden" name="rid" value="${rid}">
	<input type ="hidden" name="type" value="problem">
	</form>
</div>
</div>
</body>
</html>
