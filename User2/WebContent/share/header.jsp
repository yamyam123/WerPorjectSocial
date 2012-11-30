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
  <link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/base.css" rel="stylesheet">
	<script src="js/jquery-1.8.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	</head>
	<body>
  <div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container">
        <a class="brand" href="user?op=signup">sign up</a>
        <c:if test = "${user.id ==null }">
        <a class="brand" href="user?op=login">log in</a>
        </c:if>
        <c:if test = "${user.id !=null }">
        <a class="brand" href="user?op=login">log out</a>
        </c:if>
        <div class="nav-collapse collapse">
          <ul class="nav">
          <%
          	for(String[] menuItem : menu) {
          		if (currentMenu != null && currentMenu.equals(menuItem[1])) {
          			out.println("<li class='active'>");
          		} else {
          			out.println("<li class=''>");
          		}
          		%>
          		<a href="user?op=<%=menuItem[1] %>&id=${user.id  }"> <%= menuItem[1] %></a>
          	<%
          	}
          %>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <div class="container" style="padding-top:50px">
		<h1>
		Soical Dating
		<img src="img/logo.jpg" alt="logo">
		</h1>
		<div class="user">
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
		</div>
 	</div>
</body>
</html>