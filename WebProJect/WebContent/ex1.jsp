<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/css; charset=utf-8">
<link href="css/stylesheet.css" rel="stylesheet" type="text/css">
<title>소셜데이팅</title>
<script src="http://connect.facebook.net/en_US/all.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>

<script>
$(document).ready(function(){
// initialize the library with the API key
FB.init({ appId: '505648586126257' });

$('#login').bind('click', function() {
FB.login(handleSessionResponse);
});

$('#logout').bind('click', function() {
FB.logout(handleSessionResponse);
});
});

// handle a session response from any of the auth related calls
function handleSessionResponse(response) {
if (response.status === 'connected') {
// the user is logged in and has authenticated your
// app, and response.authResponse supplies
// the user's ID, a valid access token, a signed
// request, and the time the access token
// and signed request each expire
//var uid = response.authResponse.userID;
//var accessToken = response.authResponse.accessToken;
alert("LogIn");
} else if (response.status === 'not_authorized') {
// the user is logged in to Facebook,
// but has not authenticated your app
} else {
// the user isn't logged in to Facebook.
alert("LogOut");
}
}
</script>

</head>
<body>


<div id ="wrap"><!-- 전체를 감싸고 있는 wrap div-->
	<div id ="header"><!-- 상단부 div -->
		<div id = "header_left">
			<img src = "./3.jpg"  alt="이곳은 로고입니다."/> <!--오류 체크시 오류발생 사진파일 첨부함 -->
		</div>
		<div id = "header_right">
			<a href="#">Sign Up</a>&nbsp;&nbsp; | &nbsp;&nbsp;
			<div>
			<button id="login">Login</button>
			<button id="logout">Logout</button>
			</div>
		</div>
	</div>
	<div id="navibar"><!-- Navibar div -->
		<ul>
			<li id="Home" class="navi"><a href="#">Home</a></li>
			<li id="ReceiveHeart" class="navi"><a href="#">받은하트</a></li>
			<li id="SentHeart" class="navi"><a href="#">보낸하트</a></li>
			<li id="Modify"class="navi"><a href="#">프로필수정</a></li>
		</ul>
	</div>
	<div id="main"><!-- 네비바를 클릭했을때 보여지는 장소 -->

	</div>
</div>
</body>
</html>
