<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

session.removeAttribute("curUser");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
<body>
<form name="form1" action="employeeAction!login.action" method="post">
<table width="100%" height="100%" >
<tr>
<td align="center">
<div style="width:451px">

<div style="width:451px; height:150px; background-image:url(imgs/login_top.png)"></div>
<div style="width:451px; height:129px; background-image:url(imgs/login_center.png); color:#ffffff; font-weight:900;">
<table width="100%" height="100%" border="0" style="color: #ffffff;font-weight: 900">
<tr>
<td align="right" width="40%">用户名：</td>
<td><input type="text" name="username" value="admin" style="width:150px"/></td>
</tr>
<tr>
<td align="right">密&nbsp;&nbsp;码：</td>
<td><input type="password" name="pwd" value="123456" style="width:150px"/></td>
</tr>
</table>

</div>
<div style="width:451px; height:130px; background-image:url(imgs/login_bottom.png)">
<table  width="100%" height="100%">
<tr>
<td align="center"><img onclick="f_submit()" onmousedown="f_down(this)" onmouseup="f_up(this)" src="imgs/login_button1.png"/></td>
</tr>
</table>
</div>
</div>
</td>
</tr>
<tr>
<td align="center"><span style="color:red">${error}</span></td>
</tr>
</table>
 </form>
<script>

function f_down(obj){
    obj.src="imgs/login_button2.png";
}
function f_up(obj){
    obj.src="imgs/login_button1.png";
}

function f_submit(){
	document.form1.submit();
}

</script>


</body>
</html>

