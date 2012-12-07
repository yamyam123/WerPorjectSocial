<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"  import="java.sql.*" 
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
   <script>
   function wrapWindowByMask(id){
	      //화면의 높이와 너비를 구한다.
	      var maskHeight = $(document).height();  
	      var maskWidth = $(window).width();
	      //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
	      $('#mask').css({'width':maskWidth,'height':maskHeight});  
	 
	      //애니메이션 효과 - 일단 1초동안 까맣게 됐다가 50% 불투명도로 간다.
	      $('#mask').fadeIn(1000);      
	      $('#mask').fadeTo("slow",0.5);    
	      //윈도우 같은 거 띄운다.
	    	 $('#'+id).show();
	      
	   }
   
   $(document).ready(function(){
	      //검은 막 띄우기
	      $('.openMask').click(function(e){
	    	  e.preventDefault();
	         wrapWindowByMask(name);
	      });
	 
	      //닫기 버튼을 눌렀을 때
	      $('.window .close').click(function (e) {  
	          //링크 기본동작은 작동하지 않도록 한다.
	          e.preventDefault();  
	          $('#mask, .window').hide(); 
	          
	      });       
	 
	      //검은 막을 눌렀을 때
	      $('#mask').click(function () {  
	          $(this).hide();  
	          $('.window').hide();
	          
	      });      
	   });

   </script>
</head>
<body>
<jsp:include page = "share/header.jsp">
<jsp:param name = "current" value = "show"/>
	</jsp:include>
	<!-- 로그 인 상태일떄 -->
	<c:if test = "${user.name !=null }">
		<div class ="wrap">
		<div class ="main">
		<a href="user?op=show&id=${user.id  }">목록 새로 고침 </a>
		<c:forEach var="ruser" items="${users }">
			<div class="user-inf">
			<div class="user-name">
				${ruser.name }
				</div>
			<div class="user-pic">
				<img src="https://graph.facebook.com/${ruser.id }/picture">
				</div>
			<div class="user-more">
				<input type="button" value="자세히 보기" onClick="wrapWindowByMask('${ruser.id }')">
			</div>
			</div>
			<div class="window" id="${ruser.id }"> <!-- 윈도우 창 -->
				<img src="https://graph.facebook.com/${ruser.id }/picture">
				<div class="info">
				<br><br><br><br>
				${ruser.name }<br>
				${ruser.email }<br>
				${ruser.say }
				</div>
 				<input type="button" class="close" value="닫기 X"/>
				<form action="user" method="POST">				
					<input type="hidden" name="type" value="gheart"/>
					<input type="hidden" name="gid" value="${user.id }">
					<input type="hidden" name="rid" value="${ruser.id }">
					<input type="hidden" name="rname" value="${ruser.name }">
					<input type="hidden" name="gname" value="${user.name }">
					<input type="submit" class="submit" value="하트보내기"/>
				</form>
			</div>	
		</c:forEach>
		<div id="mask"></div> 
		</div>
		</div>
	</c:if>
	<!-- 로그 아웃 상태일떄 -->
	<c:if test = "${user.name ==null }">
	<div class ="wrap">
	<div class ="main">
	${error }
	<a class="brand" href="user?op=login">log in</a>
	</div>
	</div>
	</c:if>
	<jsp:include page = "share/footer.jsp" />
</body>
</html>
