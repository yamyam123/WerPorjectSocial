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
        	'<img src="https://graph.facebook.com/' + response.id + '/picture">' 
        	+ response.name;
      });
    }
  </script>
  
  <script>
  function getUserFriends() {
    FB.api('/me/friends&fields=name, picture', function(response) {
      console.log('Got friends: ', response);
      if (!response.error) {
        markup = '';
        var friends = response.data;
        current=0;
        for (var i=0; i < friends.length && i <6; i++) {
          var friend = friends[i];

          markup +='<div class="friend"><img src="https://graph.facebook.com/'+ friend.id +'/picture">'+friend.name +'</div>'+
         						'<div class="homelink"><img src="https://graph.facebook.com/'+ friend.id +'/picture"><a href = "https://www.facebook.com/'+ friend.id+'/"> 홈피연결  </a></div>';
        }
        current+=6;
        document.getElementById('user-friends').innerHTML = markup;
      }
    });
  }
  function viewmore()
  {
	  FB.api('/me/friends&fields=name, picture', function(response) {
	      console.log('Got friends: ', response);
	      if (!response.error) {
	        var friends = response.data;
	        for (var i=current; i < friends.length && i < current+6; i++) {
	          var friend = friends[i];

	          markup +='<div class="friend"><img src="https://graph.facebook.com/'+ friend.id +'/picture">'+friend.name +'</div>'+
	         						'<div class="homelink"><img src="https://graph.facebook.com/'+ friend.id +'/picture"><a href = "https://www.facebook.com/'+ friend.id+'/"> 홈피연결  </a></div>';
	        }
	        current+=6;
	        document.getElementById('user-friends').innerHTML = markup;
	      }
	    });
  }
  </script>
  
<script>
  function sendRequest() {
    FB.ui({
      method: 'apprequests',
      message: 'invites you to learn how to make your mobile web app social',
      link : 'http://localhost:8080/ppp2/ex1.jsp',
    	picture : 'http://www.facebookmobileweb.com/hackbook/img/facebook_icon_large.png'
    }, 
    function(response) {
      console.log('sendRequest response: ', response);
    });
  }
  </script>
  
   <script>
  function publishStory() {
    FB.ui({
      method: 'feed',
      name: '저는 소셜 모바일 웹앱을 만들고 있답니다.',
      caption: '양재기가 만들어가는 웹페이지랍니다. ',
      description: '당신도 만들고 싶다면 페이스북 개발자 사이트를 확인하세요.',
      link: 'http://localhost:8080/ppp2/ex1.jsp',
      picture: 'http://www.facebookmobileweb.com/hackbook/img/facebook_icon_large.png'
    }, 
    function(response) {
      console.log('publishStory response: ', response);
    });
    return false;
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
		<input type="button" value = "담벼락에 자랑하기" onclick="publishStory();">
		 <input type="button" value="친구 초대" onclick="sendRequest();">
  	<div id="user-friends"></div>
  	<input type="button" value="더 보기" onClick="viewmore();"/>
	</div>
</div>
</body>
</html>
