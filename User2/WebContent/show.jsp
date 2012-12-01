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
  <script src="http://code.jquery.com/jquery-latest.js"></script>
   <!--스타일 시트에 넣을부분-->
   <style>
   #mask {  
     position:absolute;  
     z-index:9000;  
     background-color:#000;  
     display:none;  
     left:0;
     top:0;
   } 
   .window{
     background-color:white;
     width:350px;
     height:500px;
     display: none;
     position:absolute;  
     left:370px;
     top:150px;
     z-index:10000;
     color:blue;
   }
 </style> 
   <script>
   function wrapWindowByMask(name){
       //화면의 높이와 너비를 구한다.
       var maskHeight = $(document).height();  
       var maskWidth = $(window).width();
       //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
       $('#mask').css({'width':maskWidth,'height':maskHeight});  
  
       //애니메이션 효과 - 일단 1초동안 까맣게 됐다가 50% 불투명도로 간다.
       $('#mask').fadeIn(1000);      
       $('#mask').fadeTo("slow",0.5);    
       //윈도우 같은 거 띄운다.
       $('#'+name).show();
       
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
 <c:if test = "${iuser.name !=null }">
  <c:forEach var="user" items="${users }">
   <div id=userinf>
    ${user.name }
    <img src="https://graph.facebook.com/${user.id }/picture">
    <input type="button" value="자세히 보기" onClick="wrapWindowByMask('${user.name }')">
   </div>
   <div class="window" id="${user.name }">
     <input type="button" class="close" value="(.window .close)"/>
    ${user.name }
    <form action="user" method="POST">
     <img src="https://graph.facebook.com/${user.id }/picture">
     <input type="hidden" name="type" value="gheart"/>
     <input type="hidden" name="gid" value="${iuser.id }">
     <input type="hidden" name="rid" value="${user.id }">
     <input type="submit" value="하트보내기"/>
    </form>
   </div> 
  </c:forEach>
  <div id="mask"></div> 
 </c:if>
 <!-- 로그 아웃 상태일떄 -->
 <c:if test = "${iuser.name ==null }">
 ${error }
 <br>
 <a class="brand" href="user?op=login">log in</a>
 </c:if>
</body>
</html>