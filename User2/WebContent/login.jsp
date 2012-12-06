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
<div class ="wrap">
<div class ="main">
	<div class="log-form">
	<form class="form-horizontal" action="user" method="POST">
	<div class="log-label">
		<label class="log-label" for="userid">ID</label>
	</div>
	<div class="log-intput">
		<input class="log-input" type="text" name="userid"><br>
	</div>
	<div class="log-label">
		<label class="log-label" for="pwd">Password</label>
	</div>
	<div class="log-intput">
		<input class="log-input" type="password" name="pwd"><br>
	</div>
		<input type ="hidden" name="type" value="log">
		<div class="login-btn">
		<input type="submit" class="btn btn-primary" value="log in">
		</div>
		<input type="hidden" name="log" value="login">
	</form>
	</div>
</div>
</div>
</c:if>
<c:if test="${log == 'logout' }">
<div class ="wrap">
<div class ="main">
	<form class="form-horizontal" action="user" method="POST">
	<input type="hidden" name="userid" value="${user.userid }">
	<input type="hidden" name="pwd" value="${user.pwd }">
	<input type="hidden" name="log" value="PUT">
	<input type ="hidden" name="type" value="log">
	<input type="submit" class="btn btn-primary" value="log out">
	</form>
	${user.name }님 환영합니다.<br><%int i =0; %>
	<c:forEach var="heart" items="${rheart }">
		<c:if test="${heart.finish ==0}">
		</c:if>
		<c:if test="${heart.finish ==1}">
		<%i++; %>
		</c:if>
	</c:forEach>
	새로운 하트 <div id="heart-number"><%=i %></div> 개
</div>
</div>

</c:if>
<jsp:include page = "share/footer.jsp" />
</body>
</html>
  <script>
   function checkHeart(id){
	      //하트가 왔을대 확인창이 뜨게함
	     if(confirm("하트가 도착했습니다. 확인하시겠습니까?")){
	    	 location.href="user?op=rshow&id="+id;
	     }
	      
	   }
   
   $(document).ready(function(){
	    		if("${log}" == 'logout'){
	    			if($("#heart-number").text()!=0 ){
	    				checkHeart("${user.id}");
	    			}
	    		}
	   });

   </script>