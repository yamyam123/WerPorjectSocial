<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="java.util.*"  import="java.sql.*" 
    import="org.apache.commons.lang3.StringUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원목록</title>
<link href="css/stylesheet.css" rel="stylesheet">
	<script src="js/jquery-1.8.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
 <script src="http://code.jquery.com/jquery-latest.js"></script>
  <script src="js/index.js"></script>
</head>
<body>
<jsp:include page = "share/header.jsp">
	<jsp:param name = "current" value = "index"/>
	</jsp:include>
<div class = "wrap">
<div class="main">

<c:if test = "${user.name !=null }">
		<img src="img/index.jpg" alt="index" width = 900px height = 500px>
	<div id="mask"></div> 
   <div class="window"> 
      <input type="button" href="#" class="close" value="닫기"/> 
		<br><br>
		★ 이용방법★ <br>
		<br>
1. 목록보기에서 이성의 사진과 프로필을 확인합니다. <br>
<br>
2. 목록에서 관심이 가는 이성에게 하트를 보냅니다.<br>
<br>
3. 상대방이 '하트' 를 수락 해주길 기다립니다. <br>
<br>
4. 수락해주면 서로간 문제를 주고 받습니다. <br>
<br>
5. 문제를 주고 받으며 핸드폰 번호를 알아냅니다.<br>
<br>
6. 만약 상대가 거절했다면 다른 이성을 택해주세요. <br>
<div class="button">
<div class="next"><h3>다음</h3></div> 
</div>
   </div> 
  
   
<div class="button">

  <div class="openMask"><h3>Manual</h3></div> 
</div>
<div class="window1">
   들어갈내용  <input type="button" href="#" class="close" value="닫기"/> </div>

</c:if>
<c:if test = "${user.name ==null }">
<img src="img/index.jpg" alt="index" width = 900px height = 500px>


  
</c:if> 
</div>
</div>
<jsp:include page = "share/footer.jsp" />
</body>
</html>