<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script  src="<%= request.getContextPath()%>/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	var  ele1 =document.getElementById("register");
	alert(ele1)
    var ele = $("#register");
  
    alert(ele)
</script>
</head>
<body>
<h1>this login page</h1>
 	<form class="form-signin" role="form" id="loginForm" method="post"
			autocomplete="off">
			<div class="form-signin-login">
				<label class="control-label" for="username">
					登录名
				</label>
				<input type="text" id="username" name="username"
					 >
				<label class="control-label" for="password">
					密码
				</label>
				<input type="password" id="password" name="password"
					>
				<button  type="submit">
					登陆
				</button>
			</div>
			<a href="#"  id = "register" name ="123">注册</a>
			
		</form>
</body>
</html>