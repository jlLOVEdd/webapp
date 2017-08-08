<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script  src="<%= request.getContextPath()%>/common/js/jquery-3.1.1.min.js"></script>
<script  src="<%= request.getContextPath()%>/common/js/md5.js"></script>
<script  src="<%= request.getContextPath()%>/common/js/token.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var loginUrl ="/login";

	$('#register').click(function(){
        alert(hex_md5($('#password').val()))
    });
	
	
	function mysubmit()
	{
		debugger;
		
		$("#password").val(hex_md5($('#password').val()));
		var username = $("#username").val();
		
		var d = $('#loginForm').serialize();

		var reqUrl = getLoginSuffixUrl(loginUrl, true);
		alert(JSON.stringify(d))
		$.ajax({
            type: "post",
            url: reqUrl,
            data: d,
            dataType:"json",
            async: true,
            success: function(data) {
            	
            	if(data.success){
            		alert("成功")
            	}else{
                 alert(data.error);
            	}
            	
            },
            error: function(data) {
            }
        });
		
		/* $("#myform").submit(); */
	}
	$("#submit").click(function(){
		mysubmit();
	})
});
</script>
</head>
<body>
<h1>this login page</h1>
 	<form  role="form" id="loginForm" method="post"
			autocomplete="off">
			<div class="form-signin-login">
				<label  for="username">
					登录名
				</label>
				<input type="text" id="username" name="username"
					 >
				<label  for="password">
					密码
				</label>
				<input type="password" id="password" name="password"
					>
				<button id ="submit" >
					登陆
				</button>
			</div>
			<a href="javascript:void(0);"  id ="register" name ="123">注册</a>
			
		</form>
</body>
</html>