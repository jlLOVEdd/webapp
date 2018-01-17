<%--
  Created by IntelliJ IDEA.
  User: js170830xlr
  Date: 2018/1/16
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>loginPage</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/common/css/layui.css"  media="all">
</head>
<body class="layui-bg-cyan">
    <div class="layui-container" style="margin-top:240px">
        <div class="layui-fluid">
            <div class="layui-row">
                <div class="layui-col-md4 layui-col-md-offset4">
                    <form class="layui-form " action="/webapp/login" method="post">
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户：</label>
                            <div class="layui-input-block">
                                <input class="layui-input"  type="text" name="username" lay-verify="required" autocomplete="off" placeholder="请输入用户名" >
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label" >密码：</label>
                            <div class="layui-input-block">
                                <input class="layui-input"  type="password" name="password" lay-verify="required" autocomplete="off" placeholder="请输入密码" >
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block" style="margin-left: 110px">
                            <button class="layui-btn" lay-submit lay-filter="go" >登陆</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/common/layui.js"></script>
    <script>
        layui.use(['form','layer'],function () {
            var form  =layui.form
                ,layer = layui.layer;
            form.on('submit(go)',function (data) {
                console(data);
                return false;
            });
        });

    </script>
</body>
</html>
