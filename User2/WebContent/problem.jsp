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
  <script>
  function onlyNumber(event) {
	    var key = window.event ? event.keyCode : event.which;    

	    if ((event.shiftKey == false) && ((key  > 47 && key  < 58) || (key  > 95 && key  < 106)
	    || key  == 35 || key  == 36 || key  == 37 || key  == 39  // 방향키 좌우,home,end  
	    || key  == 8  || key  == 46 ) // del, back space
	    ) {
	        return true;
	    }else {
	        return false;
	    }    
	};
	function select(){
		var pro = parseInt($(".select").val());
		var output='문제<input type="text" name="title"><input type="hidden" name="pnumber" value="'+pro+'"><br>';
		var problem="";
		if(!isNaN(pro)){
			for(var i=0; i<pro; i++)
			{
				problem ='지문 '+(i+1)+'번'+'<input type="text" name="'+(i+1)+'">'+
							'<input type="radio" name="answer" value="'+(i+1)+'"checked="checked">정답 ';
				output += problem;
			}
			output +=  '<br><input type="submit" value="문제 제출">';
				$(".space").html(output);
		}
	}
  </script>
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
