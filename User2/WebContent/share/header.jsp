<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
	String[][] menu = {
		{"./user", "index" },
		{"./user", "show" },
		{"#", "Gheart" },
		{"#", "Rheart" }
	};

  String currentMenu = request.getParameter("current");
%>    
	<!-- Navbar
  ================================================== -->
  <!DOCTYPE html>
	<html>
	<head>
  
	<link href="css/stylesheet.css" rel="stylesheet">
	<script src="js/jquery-1.8.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	</head>
	<body>
	<div id ="wrap">
	<div id ="header">
		
		<div id="header_left">
		<img src="img/logo.jpg" alt="logo">
		</div>
	<div id = "header_right">
	<li>
		<c:if test = "${user.id != NULL}">
			${user.name   } <img src="https://graph.facebook.com/${user.id }/picture">
			<form class="form-horizontal" action="user" method="POST">
			<input type="hidden" name="userid" value="${user.userid }">
			<input type="hidden" name="pwd" value="${user.pwd }">
			<input type="hidden" name="log" value="PUT">
			<input type ="hidden" name="type" value="log">
			<input type="submit" class="btn btn-primary" value="log out">
			</form>
		</c:if>
		</li>
		</div>
	</div>
	
	<div id = "navibar">
	<ul>
        <li class="navi"><a href="user?op=signup" >sign up</a></li>
        
        <c:if test = "${user.id ==null }">
        <li class="navi"><a href="user?op=login">log in</a></li>
        </c:if>
        
        <c:if test = "${user.id !=null }">
        <li class="navi"><a href="user?op=login">log out</a></li>
        </c:if>
        
          
          <%
          	for(String[] menuItem : menu) {
          		if (currentMenu != null && currentMenu.equals(menuItem[1])) {
          			out.println("<li class='navi'>");
          		} else {
          			out.println("<li class=''>");
          		}
          		%>
          		<li class='navi'><a href="user?op=<%=menuItem[1] %>&id=${user.id  }"> <%= menuItem[1] %></a></li>
          	<%
          	}
          %>
         
          </ul>
  </div>
 
  
		
	
 	
 	</div>
</body>
</html>