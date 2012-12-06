<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/stylesheet.css" rel="stylesheet">
<title>Insert title here</title>
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
   function moveProblem(id,rid){
	   location.href="user?op=problem&id="+id+"&problem="+rid;
   }
   function moveProblemShow(id,rid){
	   location.href="user?op=pshow&id="+id+"&problem="+rid;
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
<jsp:include page="share/header.jsp">
  <jsp:param name="current" value="rshow"/>
  </jsp:include>
 
<c:if test = "${user.name !=null }">
 <div class ="wrap">
 <div class ="main">
 <table>
 <tr>
 <th>보낸 시간</th>
 <th>보낸 사람</th>
 <th>사진</th><th></th>
 </tr>
	
	<c:forEach var="heart" items="${rheart }">
	<tr>
	<td> ${heart.receiveTime }</td>
	<td> ${heart.gName }</td>
	<td><img src="https://graph.facebook.com/${heart.gId }/picture"></td>
	<td><input type="button" value="자세히 보기" onClick="wrapWindowByMask('${heart.gId }')">
	<c:if test = "${heart.phrase == 1}">
	<input type="button" value="문제내기" onClick="moveProblem('${user.id }','${heart.gId }')">
	</c:if>
	<c:if test = "${heart.phrase == 2}">
	<input type="button" value="문제보기" onClick="moveProblemShow('${user.id }','${heart.gId }')">
	</c:if>
	</td>
	</tr>
	<div class="window" id="${heart.gId }"> <!-- 윈도우 창 -->
				<img src="https://graph.facebook.com/${heart.gId }/picture">
				<div class="info">
				<br><br><br><br>
				${heart.gName }<br>
				</div>
 				<input type="button" class="close" value="닫기 X"/>
				<form action="user" method="POST">				
					<input type="hidden" name="type" value="gheart"/>
					<input type="hidden" name="rid" value="${heart.gId }">
					<input type="hidden" name="rname" value="${heart.gName }">
					<input type="submit" class="submit" value="하트보내기"/>
				</form>
				</div>
	</c:forEach>
	</table>
	<div id="mask"></div> 
</div>
</div>
</c:if>


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