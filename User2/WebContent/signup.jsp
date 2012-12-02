<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
  <meta property="og:title" content="안녕 친구들" />
  <meta property="og:type" content="website" />
  <meta property="og:site_name" content="안녕 친구들" />
  <meta property="og:description" content="안녕 친구들! 이것은 내 모바일 웹앱 예제랍니다." />
  <meta property="og:image" content="http://www.facebookmobileweb.com/hackbook/img/facebook_icon_large.png"/>
	<title>회원목록</title>
	<link href="css/stylesheet.css" rel="stylesheet">
	<script src="js/jquery-1.8.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="share/header.jsp">
  <jsp:param name="current" value="Sign Up"/>
</jsp:include>
 <div class="wrap">
	<div class="main">
		<ul>
 			<li id="login">페이스북에 로그인 해주세요 <button onClick="loginUser();">Login</button></li>
			<li id="logout">페이스북 로그아웃 하기 <button onClick="FB.logout();">Logout</button></li>
 		</ul>
		<div></div>
   
		  <form class="form-horizontal" action="user" method="POST">
			<fieldset>
			<input type="hidden" name="type" value="signup"/>
        <legend class="legend">Sign Up</legend>
        <c:if test="${method == 'PUT'}">
          <input type="hidden" name="_method" value="PUT"/>
        </c:if>
				<div class="control-group">
					<label class="control-label" for="userid">ID</label>
					<div class="controls">
						<input type="text" name="userid">
					
					</div>
				</div>
				<c:if test="${method == 'POST'}">
					<%-- 신규 가입일 때만 비밀번호 입력창을 나타냄 --%>
					<div class="control-group">
						<label class="control-label" for="pwd">Password</label>
						<div class="controls">
							<input type="password" name="pwd">
						</div>
					</div>
	
					<div class="control-group">
						<label class="control-label" for="pwd_confirm">Password Confirmation</label>
						<div class="controls">
							<input type="password" name="pwd_confirm">
						</div>
					</div>
				</c:if>
				<div class="control-group">
					<label class="control-label" for="name">Name</label>
					<div class="controls">
						<div id="user-name"></div>
				
					</div>
				</div>

				
				<div class="control-group">
					<label class="control-label" for="email">E-mail</label>
					<div class="controls">
						<div id="user-email"></div>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">Gender</label>
					<div class="controls">
					      <div id="user-gender"></div>
					</div>
				</div>
				
				<div id="user-hidden"></div>
				<div class="form-actions">
					<a href="user" class="btn" onClick="FB.logout();">목록으로</a>
					<c:choose>
					  <c:when test="${method=='POST'}">
  						<input type="submit" class="btn btn-primary" value="가입">
  					</c:when>
  					<c:otherwise>
  						<input type="submit" class="btn btn-primary" value="수정">
  					</c:otherwise>
  				</c:choose>
				</div>
			</fieldset>
		  </form>
    </div>
  </div>
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
       join(response);
     }
   }
   </script>
	
	<script>
    function loginUser() {    
      FB.login(function(response) { }, {scope:'email'});        
    }
  </script>
  
  <style>
   	#logout{display:none;}
    body.connected #login { display: none; }
    body.connected #logout { display: block; }
    body.not_connected #login { display: block; }
    body.not_connected #logout { display: none; }
    body.not_connected #user-name { display: none; }
    body.connected #user-name{ display: block; }
    body.not_connected #user-email { display: none; }
    body.connected #user-email{ display: block; }
  </style>
  
  <script>
    function updateUserInfo(response) {
      FB.api('/me', function(response) {
       
        document.getElementById('user-hidden').innerHTML = '<input type="hidden" name="id" value="'+ response.id +'"><input type="hidden" name="name" value="'+response.name+'"><input type="hidden" name="gender" value="'+response.gender
+'"><input type="hidden" name="email" value="'+response.email+'"><input type="hidden" name="img" value="https://graph.facebook.com/"'+response.id+'"/picture">';
      });
    }
    </script>
		<script>
			function join(response){
				FB.api('/me',function(response){
				   document.getElementById('user-name').innerHTML = response.name;
				   document.getElementById('user-gender').innerHTML = response.gender;
				   document.getElementById('user-email').innerHTML = response.email;
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

          markup += '<img src="https://graph.facebook.com/' + friend.id + '/picture"> ' + friend.name + '<br>';
        }
        document.getElementById('user-friends').innerHTML = markup;
      }
    });
  }
  </script>
  <jsp:include page = "share/footer.jsp" />
</body>
</html>