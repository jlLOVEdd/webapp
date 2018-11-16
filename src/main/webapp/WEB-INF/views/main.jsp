<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/page/include/taglib.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>个人项目测试</title>
    <link rel="stylesheet" href="common/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">layui 后台布局</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>

    <div id="menuView">

    </div>


    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;"><div class="layui-container" style="margin-top:240px">
            <div class="layui-fluid">
                <div class="layui-row">
                    <div class="layui-col-md4 layui-col-md-offset4">
                        <form class="layui-form " action="login" method="post">
                            <div class="layui-form-item">
                                <label class="layui-form-label">时间：</label>
                                <div class="layui-input-block">
                                    <input class="layui-input"  type="date" name="username" lay-verify="required" autocomplete="off" placeholder="请输入用户名" >
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">97时间：</label>
                                <div class="layui-input-block">
                                    <input class="layui-input" name="username" lay-verify="required" autocomplete="off" placeholder="请输入用户名" >
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
            </div></div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>

<script>

    //JavaScript代码区域
    layui.use(['element', 'laytpl'], function () {
        var element = layui.element,
            laytpl = layui.laytpl,
            $ = layui.jquery;
        var data =[];
        $.ajax({
            url: "resources/selectAllMenu",
            data:[],
            type:"POST",
            dataType:"json",
            success:function(data11){
                data=data11;
                var template = [
                    '<div class="layui-side layui-bg-black">'
                    , '<div class="layui-side-scroll">'
                    , '{{# if(d.data && d.data.length > 0) { }}'
                    , '{{# layui.each(d.data, function(index, topMenu) { }}'
                    , '<ul class="layui-nav layui-nav-tree" lay-filter="menuFilter">'
                    , '<li class="layui-nav-item layui-nav-itemed">'
                    , '<a class="" href="javascript:;">{{ topMenu.name }}</a>'
                    , '{{# if(topMenu.childrenList && topMenu.childrenList.length > 0) { }}'
                    , '<dl class="layui-nav-child">'
                    , '{{# layui.each(topMenu.childrenList, function(i, childMenu) { }}'
                    , '<dd><a href="javascript:;">{{ childMenu.name }}</a></dd>'
                    , '{{# }); }}'
                    , '</dl>'
                    , '{{# } }}'
                    , '</li>'
                    , '</ul>'
                    , '{{# }); }}'
                    , '{{# } }}'
                    , '</div>'
                    , '</div>'
                ].join('');
                var view = $('#menuView');
                laytpl(template).render(data, function (html) {
                    view.html(html);
                });
                element.init();
            }
    });

    });
</script>
</body>
</html>
