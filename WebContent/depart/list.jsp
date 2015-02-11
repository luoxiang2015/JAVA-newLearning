<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>岗位列表</title>
<link rel="stylesheet" type="text/css" href="../css/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="../css/themes/icon.css"/>

	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../js/json.js"></script>
	
	<script>
	var curindex=-1;
	function f_formate(value,rowdata,index){
		
		return "<a onclick=\"f_initupdate("+rowdata.id+","+index+")\">修改</a>&nbsp;&nbsp;<a onclick=\"f_delete("+rowdata.id+","+index+")\">删除</a>";
	}
	function f_formateDate(d){
		return d.getYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
	}
	
	var curid=0;
	function f_initupdate(id,index){//点击修改时弹出修改窗口
		curindex=index; //记录当前行的索引
		curid=id;
		$('#w').window({title:"修改"});
		$('#w').window('open');
		$('#ff').form('clear');
		$("#ff").form("load","departAction!load.action?id="+id+"&"+new Date());
		
	}
	
	function f_add(){
		$('#w').window({title:"添加"});
		$('#w').window('open');
		$('#ff').form('clear');
		curindex=-1;//清除当前行的索引
		curid=0;
		$('#datalist').datagrid("unselectAll");
	}
	
	function f_delete(id,index){//删除行
		
		
		$.messager.confirm('确认删除','是否确定删除？',function(r){ //弹出确认框  
			if (r){  //如果确认删除，发送异步请求删除数据 
				$.get("departAction!delete.action?id="+id+"&"+new Date(),function(data){
					if(parseInt(data)==1){//请求成功后删除列表中的行
						//$("#datalist").datagrid("deleteRow",index);
						index=$("#datalist").datagrid("getRowIndex",id);
						$("#datalist").datagrid("deleteRow",index);
					}
				}); 
			}   
		});  

	}
	
	$(function(){
		
		$('#datalist').datagrid({
			url:'departAction!query.action?'+new Date(),
			idField:'id',
			rownumbers:true,fit:true,fitColumns:true,method:'post',noheader:true,singleSelect:true,
			columns:[[   
	        {field:'id',title:'id',width:100,hidden:true},   
	        {field:'number',title:'编号',width:100},   
	        {field:'name',title:'名称',width:100,align:'right'}   ,
	        {field:'test',title:'操作',width:100,align:'right',formatter:f_formate}   
	    ]]   

		});
	});
	
/* $(function(){
		
		$('#queryTable').datagrid({
			url:'departAction!query.action?'+new Date(),
			idField:'id',
			rownumbers:true,fit:true,fitColumns:true,method:'post',noheader:true,singleSelect:true,
			columns:[[   
	        {field:'id',title:'id',width:100,hidden:true},   
	        {field:'number',title:'编号1',width:100},   
	        {field:'name',title:'名称1',width:100,align:'right'}   ,
	        {field:'test',title:'操作1',width:100,align:'right',formatter:f_formate}   
	    ]]   

		});
	}); */
	
	</script>
	
</head>

<body>
<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="f_add()">新建</a>
<table id="datalist"></table>

<form id="fff" name="fff"  method="post">

<table>
	    		<tr>
	    			<td>编号:</td>
	    			<td><input class="easyui-validatebox" type="text" name="number" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>岗位名:</td>
	    			<td><input class="easyui-validatebox" type="text" name="name" data-options="required:true"></input></td>
	    		</tr>
				
				
</table>
<div style="padding:5px">
<input type="submit" value="submit"/>
   	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a>
   	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Clear</a>
   </div>
</form>
<!--  <table class="easyui-datagrid" title="Basic DataGrid" id="datalist" 
			data-options="url:'departAction!query.action?',idField:'id',rownumbers:true,fit:true,fitColumns:true,method:'post',noheader:true,singleSelect:true">
		<thead>
			<tr>
				<th data-options="field:'id',hidden:true,width:100">id</th>
				<th data-options="field:'number',width:100">编号</th>
				<th data-options="field:'name',width:80,align:'right'">名称</th>
				<th data-options="field:'test',width:100,align:'center',formatter:f_formate">操作</th>
			</tr>
		</thead>
	</table>-->
	
	
	<div id="w" class="easyui-window" title="编辑窗口" align="center"
	data-options="modal:true,closed:true,iconCls:'icon-edit',collapsible:false,minimizable:false" 
	style="width:300px;height:200px;padding:10px;">
		<form id="ff" name="ff"  method="post">
		<input type="hidden" name="id"/><br><br>
	<table>
	    		<tr>
	    			<td>编号:</td>
	    			<td><input class="easyui-validatebox" type="text" name="number" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>岗位名:</td>
	    			<td><input class="easyui-validatebox" type="text" name="name" data-options="required:true"></input></td>
	    		</tr>
				
				
</table>
<div style="padding:5px">
	    	
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">reset</a>
	   	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a>
	    </div>

</form>
</div>
<script>

	
		function submitForm(){//提交操作
			$("#ff").form("submit",{
				url:"departAction.action",
				success:function(data){//服务器成功返回时回调方法
					var d=JSON.parse(data); //把服务器返回的数据转换成json对象
					$('#w').window('close');//关闭窗口
					if(curindex==-1){//判断是添加还是修改
						$("#datalist").datagrid("appendRow",d);//在列表中追加一行
					}else{
						//alert(eval(data));
						$("#datalist").datagrid("updateRow",{index:curindex,row:d});//修改指定行的数据
					}
				}
			});
			//alert($("#datalist").datagrid("getSelected"));
		}
		function clearForm(){//重新加载数据
			if(curid==0)
				$("#ff").form("clear");
			else
				$("#ff").form("load","departAction!load.action?id="+curid);
		}
	</script>


</body>
</html>
