<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link href="css/stylesheet.css" rel="stylesheet">
  <script src="js/jquery-1.8.2.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
<title>Insert title here</title>

</head>
<body>
<jsp:include page="share/header.jsp">
  <jsp:param name="current" value="gshow"/>
  </jsp:include>
 
<c:if test = "${user.name !=null }">
<div class="wrap">
<div class="main">
 <table>
 <th>보낸 시간</th>
 <th>받은 사람</th>
 <th>상 태 </th>
	<c:forEach var="heart" items="${gheart }">
	<tr>
	<td> ${heart.giveTime }</td>
	<td> ${heart.rName }</td>
	<td>

		<c:if test="${heart.finish ==0}">
		대기
		</c:if>
		<c:if test="${heart.finish ==1}">
		성공
		</c:if>
	</td>
	</tr>
	</c:forEach>
	</table>
	</div>
	</div>
</c:if>

<c:if test = "${user.name ==null }">
<div class="wrap">
<div class="main">
${error }
<a class="brand" href="user?op=login">log in</a>
</div>
</div>
</c:if>
<jsp:include page = "share/footer.jsp" />
</body>
</html>