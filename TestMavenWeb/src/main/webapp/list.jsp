<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 如果验证成功返回信息是用户名，就可以这样简写，如果返回的是一个pojo，那么需要写pojo的对应属性，如：name -->
	登陆成功！欢迎：<shiro:principal></shiro:principal>
	<a href="shiro/logout">登出</a>
	<br><br>
	
	<!-- 如果当前登陆成功的用户具有admin 权限，那么显示对应的界面 -->
	<shiro:hasRole name="admin">
	<a href="admin.jsp">to admin page</a>
	<br><br>
	</shiro:hasRole>
	
	<!-- 如果当前登陆成功的用户具有user 权限，那么显示对应的界面 -->
	<shiro:hasRole name="user">
	<a href="user.jsp">to user page</a>
	<br><br>
	</shiro:hasRole>
	
</body>
</html>