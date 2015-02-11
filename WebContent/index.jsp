<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>云教育</title>
<link rel="stylesheet" type="text/css" href="css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/demo.css">

	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	
	<script>
	<!-- 修改密码函数  -->
	function submitForm(){
		$('#w').window('close');
		
		<!--alert("修改成功");-->
		$('#ww').window('open');
		};
	function affirm(){
		$('#ww').window('close');
	};
	function f_add(title,url){
	
		if($('#content').tabs('exists',title)){
			$('#content').tabs('select',title);
		}else{
		
	
		$('#content').tabs('add',{
				title: title,
				content: '<iframe  src="'+url+'"  frameborder="0" height="100%" width="100%"></iframe>',
				closable: true
			});
		}
	}
	
	function f_changePwd(){
		$('#w').window('open');
	
	}
	
	
	</script>

</head>



<body class="easyui-layout">

<div data-options="region:'north'" style=" height:100px;background-image:url(imgs/bg.jpg)">

<div style="width:523px;height:98px;background-image:url(imgs/logo.jpg);float: left"></div>

<div style="width:100%;height:98px;" >
<div style="float: right">
<table style="height:98px;margin-right: 40px">
<tr>                                                                                     <!-- session.getAttribute(key)  sessionScope.curUser.username -->
<td colspan="6" align="center"><span style="color:#ffffff;font-size:16px;font-weight: 600">欢迎:${sessionScope.curUser.username }！</span></td>
</tr>
<tr>
<td><img alt="" src="imgs/msg_hot.png"></td>
<td>新消息4条！&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><img alt="" src="imgs/logout.png"></td>
<td><a href="employeeAction!login.action" style="curse: pointer">退出</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><img alt="" src="imgs/edit.png"></td>
<td><a onclick="f_changePwd()" style="cursor: pointer">修改密码</a></td>
</tr>
</table>
</div>
</div>


</div>
<div data-options="region:'west',split:true,title:'菜单栏'" style=" width:200px; height:300px">

<div class="easyui-accordion" data-options="fit:true,selected:false">

<%
List<Object[]> res=(List<Object[]>)request.getAttribute("nodes");


%>
<div title="教学管理" >
<%
for(int i=0;i<res.size();i++){
	
	Object[] temp=res.get(i);
	int type=(Integer)temp[1];
	if(type==-1){
		System.out.println("教学管理9999"+temp[0]);
%>
<a href="javascript: f_add('<%=temp[0] %>','<%=temp[2] %>')" class="easyui-linkbutton" style="width:100%;" data-options="plain:true"><%=temp[0] %></a><br>

<%} }%>
</div>

<div title="系统管理" >
<%
for(int i=0;i<res.size();i++){
	Object[] temp=res.get(i);
	int type=(Integer)temp[1];
	if(type==-2){

%>
<a href="javascript: f_add('<%=temp[0] %>','<%=temp[2] %>')" class="easyui-linkbutton" style="width:100%;" data-options="plain:true"><%=temp[0] %></a><br>

<%} }%>
</div>

<!-- <div title="教学管理" >
<a href="javascript: f_add('岗位管理','depart/list.jsp')" class="easyui-linkbutton" style="width:100%;" data-options="plain:true">岗位管理</a><br>

<a href="javascript: f_add('大纲管理','catalog/tree.jsp')" class="easyui-linkbutton" style="width:100%" data-options="toggle:true,plain:true">大纲管理</a><br>
<a href="javascript: f_add('讲义管理','lecture/lecture.jsp')" class="easyui-linkbutton" style="width:100%" data-options="toggle:true,plain:true">讲义管理</a><br>
</div>

<div title="系统管理">
<a href="javascript: f_add('人员管理','employee/list.jsp')" class="easyui-linkbutton" style="width:100%" data-options="toggle:true,plain:true">人员管理</a><br>
<a href="javascript: f_add('角色管理','role/role.jsp')" class="easyui-linkbutton" style="width:100%" data-options="toggle:true,plain:true">角色管理</a><br>
<a href="javascript: f_add('权限管理','node/node.jsp')" class="easyui-linkbutton" style="width:100%" data-options="toggle:true,plain:true">权限管理</a><br>
</div> -->


</div>

</div>
<div data-options="region:'center'" >


<div id="content" class="easyui-tabs" data-options="fit:true" >




</div>





</div>
<!--<div data-options="region:'east'" style=" background-color:#66CC66; height:300px; width:100px">右边</div>
<div data-options="region:'south'" style="background-color:#CC9900; height:50px;">底部</div>-->

<div id="w" class="easyui-window" title="修改密码" align="center"
	data-options="modal:true,closed:true,iconCls:'icon-edit',collapsible:false,minimizable:false" 
	style="width:300px;height:200px;padding:10px;">
		<form id="ff" name="ff"  method="post">
	<table>
	    		<tr>
	    			<td>原密码:</td>
	    			<td><input class="easyui-validatebox" type="password" name="number" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>新密码:</td>
	    			<td><input class="easyui-validatebox" type="password" name="name" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>重复新密码:</td>
	    			<td><input class="easyui-validatebox" type="password" name="name" data-options="required:true"></input></td>
	    		</tr>
				
				
</table>
<div style="padding:5px">
	    	
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">reset</a>
	   	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a>
	    </div>

</form>
</div>
<!-- 成功窗口 -->
<div id="ww" class="easyui-window" title="消息提示" align="center" 
	data-options="modal:true,closed:true,iconCls:'icon-edit',collapsible:false,minimizable:false" 
	style="width:250px;height:150px;padding:10px;">
		<form action="">
		<div style="height:60px;">
		 修改成功！
		</div>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="affirm()">确认</a> 	
		</form>
		

</div>





</body>
</html>
