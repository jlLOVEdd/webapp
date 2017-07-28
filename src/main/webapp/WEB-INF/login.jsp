<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isELIgnored="false" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 	<form class="form-signin" role="form" id="loginForm" action="/user/dologin" method="post"
			autocomplete="off">
			<div class="form-signin-login">
				<label class="control-label" for="username">
					登录名
				</label>
				<input type="text" id="username" name="username"
					class="input-block-level required form-control" >
				<label class="control-label" for="password">
					密码
				</label>
				<input type="password" id="password" name="password"
					class="input-block-level required form-control">
				<button class="btn btn-lg btn-info btn-block" type="submit">
					登陆
				</button>
			</div>
			
		</form>
</body>
</html>