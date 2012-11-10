<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html lang="ko">
<head>
<link href="./stylesheet.css" rel="stylesheet" type="text/css">
<title>안녕 친구들</title>
  <meta charset="utf-8" />
  <meta name="viewport" content="initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
  <meta property="og:title" content="안녕 친구들" />
  <meta property="og:type" content="website" />
  <meta property="og:site_name" content="안녕 친구들" />
  <meta property="og:description" content="안녕 친구들! 이것은 내 모바일 웹앱 예제랍니다." />
  <meta property="og:image" content="http://www.facebookmobileweb.com/hackbook/img/facebook_icon_large.png"/>
</head>
<body>
<div id="fb-root"></div>
  <script>
    (function() {
      var e = document.createElement('script'); e.async = true;
          e.src = document.location.protocol + '//connect.facebook.net/en_US/all.js';
          document.getElementById('fb-root').appendChild(e);
          }());
  </script>
  
  <script>
    window.fbAsyncInit = function() {
      FB.init({ appId: '505648586126257', 
      status: true, 
      cookie: true,
      xfbml: true,
      oauth: true});
 
      FB.Event.subscribe('auth.statusChange', handleStatusChange);      
    };
  </script>
  
  <script>
   function handleStatusChange(response) {
     document.body.className = response.authResponse ? 'connected' : 'not_connected';
 
     if (response.authResponse) {
       console.log(response);
       updateUserInfo(response);
       getUserFriends();
     }
   }
   </script>
	
	<script>
    function loginUser() {    
      FB.login(function(response) { }, {scope:'email'});        
    }
  </script>
  
  <style>
    body.connected #login { visibility:hidden; }
    body.connected #logout { visibility:visible; }
    body.not_connected #login { visibility:visible; }
    body.not_connected #logout { visibility:hidden; }
    body.not_connected #user-info{visibility:hidden;}
    body.not_connected .friend{visibility:hidden;}
  </style>
  
  
  
  <script>
    function updateUserInfo(response) {
      FB.api('/me', function(response) {
        document.getElementById('user-info').innerHTML = 
        	'<img src="https://graph.facebook.com/' + response.id + '/picture">' + response.name;
      });
    }
  </script>
  
  <script>
  function getUserFriends() {
    FB.api('/me/friends&fields=name, picture', function(response) {
      console.log('Got friends: ', response);
      if (!response.error) {
        var markup = '';
        var friends = response.data;
        for (var i=0; i < friends.length && i < 25; i++) {
          var friend = friends[i];

          markup +='<div class="friend"><img src="https://graph.facebook.com/' + friend.id + '/picture">' + friend.name+'</div>';
        }
        document.getElementById('user-friends').innerHTML = markup;
      }
    });
  }
  </script>
  
<div id ="wrap"><!-- 전체를 감싸고 있는 wrap div-->
	<div id ="header"><!-- 상단부 div -->
		
		<div id="header_left">
			<img src = "./3.jpg"  alt="이곳은 로고입니다."/> <!--오류 체크시 오류발생 사진파일 첨부함 -->
		</div>
		
		<div id="header_right">
		<ol>
			<li><a href="#">Sign Up</a></li>
			<li id="login"><a href="#" onClick="loginUser();">Login</a></li>
   		<li id="logout"><a href="#" onClick="FB.logout();">Logout</a></li>
   	</ol>			
		
		</div>
	</div>
	<div id="navibar"><!-- Navibar div -->
		<ul>
			<li id="Home" class="navi"><a href="#">Home</a></li>
			<li id="ReceiveHeart" class="navi"><a href="#">받은하트</a></li>
			<li id="SentHeart" class="navi"><a href="#">보낸하트</a></li>
			<li id="Modify"class="navi"><a href="#">프로필수정</a></li>
		</ul>
		<div id="user-info"></div>
	</div>
	<div id="main"><!-- 네비바를 클릭했을때 보여지는 장소 -->
  	<div id="user-friends"></div>
	</div>
</div>
</body>
</html>
