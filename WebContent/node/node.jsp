<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>权限列表</title>
<link rel="stylesheet" type="text/css" href="../css/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="../css/themes/icon.css"/>

	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="../js/json.js"></script>
	
	<script>
	function f_formate(value,rowdata,index){
			
			return "<a onclick=\"f_show("+rowdata.id+")\">查看</a>&nbsp;&nbsp;<a onclick=\"f_update("+rowdata.id+","+index+")\">修改</a>&nbsp;&nbsp;<a onclick=\"f_delete("+rowdata.id+")\">删除</a>";
	}
	
	function f_typeformate(value){
		return f_changeType(value);
	}
	
	function f_changeType(type){
		switch(type){
		    case -1:
		    	return "教学管理";
		    case -2:
		    	return "系统管理";
		    default:
		    	return "";
		}
	}
	
	//添加按钮
	var curindex=-1;
	function f_add(){
		curindex=-1;
		$('#w').window({title:"添加新权限"});
		$('#w').window('open');//
	}
	//打开搜索窗口
	function f_openSearch(){
		$('#sw').window('open');//
	}
	//执行搜索
	var searchparam;
	function f_search(){
		searchparam={  //设置搜索参数
				"name":$("#con_nodeName").val(),
				"type":$("#con_type").val()
			};
		$("#datalist").datagrid({ //执行搜索
			queryParams:searchparam,
			url:"nodeAction!queryAll.action?"+new Date(), 
			pageNumber:1    //从第一页开始                
		});
		$('#sw').window('close');
		
	}
	//取消当前条件
	function f_clearSearch(){
		$('#sf').form('clear');//清空表单数据
		$('#sw').window('close');//
		searchparam={};//清空查询条件
		$("#datalist").datagrid({ //执行搜索
			queryParams:searchparam,
			url:"nodeAction!queryAll.action?"+new Date(), 
			pageNumber:1    //从第一页开始                
		});
	}
	
	//查看
	function f_show(id){
		$.ajax({
			type: 'post',
	        // 处理请求的URI
	        url: 'nodeAction!load.action?id='+id,
	        // 服务端返回的数据格式，一般是json,text,xml
	        dataType: 'text',
	        error: function() {
	        	$.messager.alert("提示","删除失败！");
	        },
	        success:function(data){
	        	if(data=='error')
	        		$.messager.alert("提示","删除失败！");
	        	else{
	        		data=unescape(data);//转换Unicode编码
					var d=JSON.parse(data);
	        		$("#l_nodeName").html(d.name);
	        		$("#l_url").html(d.url);
	        		$("#l_type").html(f_changeType(d.type));
	        		$("#l_remark").html(d.remark);
	        		$('#lw').window('open');//
	        	}
	        }
	   });
	}
	
	//修改
	function f_update(id,index){
		curindex=index;//记录当前索引，以便修改
		$('#w').window({title:"修改权限"});
		$('#w').window('open');
		$("#ff").form("load","nodeAction!load.action?id="+id+"&"+new Date());
		
	}
	
	//删除
	function f_delete(id){
		
		$.messager.confirm("提示","是否确定删除？",function(r){
			if(r){
				var num=$('#datalist').datagrid('options').pageNumber;
				var size=$('#datalist').datagrid('options').pageSize;
				var hasNext=false;
				var page=0;
				if((dataTotal-1)>=(num*size)){//判断是否有下一页
					hasNext=true;
					page=num*size;//计算下一页第一个数据
				}else{
					hasNext=false;
				}
				//执行删除操作
				$.ajax({
			type: 'post',
	        // 处理请求的URI
	        url: 'nodeAction!delete.action?id='+id+"&hasNext="+hasNext+"&page="+page,
	        // 传递给服务端的数据（参数）
	        data: searchparam,
	        // 服务端返回的数据格式，一般是json,text,xml
	        dataType: 'text',
	        error: function() {
	        	$.messager.alert("提示","删除失败！");
	        },
	        success: function(data) {
	        	if(data=='error')
	        		$.messager.alert("提示","删除失败！");
	        	else{
	        		dataTotal--;
	        		
	        		var index=$("#datalist").datagrid("getRowIndex",id);
					$("#datalist").datagrid("deleteRow",index);//删除当前行
	        		if(hasNext&&data!="null"){//如果有下一页，把下一页第一条数据添加到当前页
	        			data=unescape(data);//转换Unicode编码
	        			
						var d=JSON.parse(data);
	        			$("#datalist").datagrid("appendRow",d);
	        			var pg = $('#datalist').datagrid('getPager');
						$(pg).pagination( {total:dataTotal});//修改总记录数，更新分页信息
	        			
	        		}else{
	        			//如果当前页已经删除完毕，刷新列表显示上一页
	        			var len=$("#datalist").datagrid("getRows").length;
	        			var p=$("#datalist").datagrid("options").pageNumber;
	        			if(len==0&&p>1){
	        				$("#datalist").datagrid({pageNumber:p--});
							$("#datalist").datagrid("reload");
							return;
						}else{
							var pg = $('#datalist').datagrid('getPager');
							$(pg).pagination( {total:dataTotal});//修改总记录数，更新分页信息
						}
	        		}
	        		
	        	}
	        		
	        }
		});
			}
		});
		
	}
	
	//提交表单
	function submitForm(){
		$('#w').window('close');//
		if(curindex==-1){//如果是添加
		$("#ff").form("submit",{
			url:"nodeAction!add.action",
			success:function(data){//
			if(data=='error'){
				$.messager.alert("提示","数据保存失败！");
				return ;
			}
			dataTotal++;
			data=unescape(data);
			var d=JSON.parse(data); //
			var size=$("#datalist").datagrid("options").pageSize;
			var len=$("#datalist").datagrid("getRows").length;
			
			if(len<size){//如果当前不足页
				$("#datalist").datagrid("insertRow",{index:0,row:d});
			}else{//如果当前页已满
				$("#datalist").datagrid("insertRow",{index:0,row:d});
				$("#datalist").datagrid("deleteRow",size);
				var p = $('#datalist').datagrid('getPager');
				
				$(p).pagination( {total:dataTotal});
			}
			
		}
					
		});
		}else{//如果是修改
			$("#ff").form("submit",{
			url:"nodeAction!update.action",
			success:function(data){//
			if(data=='error'){
				$.messager.alert("提示","数据保存失败！");
				return ;
			}
			data=unescape(data);
			var d=JSON.parse(data); //
			
			$("#datalist").datagrid("updateRow",{index:curindex,row:d});//修改指定行的数据
			}
			});
		}
	}
	
	function clearForm(){
		if(curindex==-1){
			$('#ff').form('clear');
			$('#ntype').val('');
		}else{
			$("#ff").form("load","nodeAction!load.action?id="+$("#eid").val()+"&"+new Date());
		}
		
	}
	
	//onload
	var dataTotal;
	$(document).ready(function(){
		$('#datalist').datagrid({
			onLoadSuccess:function(data){
				dataTotal=data.total;
			}
		});
		$('#w').window({
			onOpen:function(){
			$('#ff').form('clear');
			$('#ntype').val('');
			}
		});//
		
	});
	
	
	</script>
	
</head>

<body>
<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="f_add()">新建</a>
<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="f_openSearch()">高级搜索</a>

	<table class="easyui-datagrid" title="Basic DataGrid" id="datalist" 
			data-options="url:'nodeAction!queryAll.action',idField:'id',rownumbers:true,fitColumns:true,method:'post',noheader:true,singleSelect:true,pagination:true">
		<thead>
			<tr>
				<th data-options="field:'id',hidden:true,width:100">id</th>
				<th data-options="field:'name',width:100,align:'center'">权限名</th>
				<th data-options="field:'type',width:80,align:'center',formatter:f_typeformate">类型</th>
				<th data-options="field:'test',width:100,align:'center',formatter:f_formate">操作</th>
			</tr>
		</thead>
	</table>
	
	
	<div id="w" class="easyui-window" title="编辑窗口" align="center"
	data-options="modal:true,closed:true,iconCls:'icon-edit',collapsible:false,minimizable:false" 
	style="width:450px;height:350px;padding:10px;">
		<form id="ff" name="ff"  method="post">
		<input type="hidden" id="eid" name="id"/><br/>
	<table>
	    		<tr>
	    			<td>权限名:</td>
	    			<td><input class="easyui-validatebox" type="text" name="name" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>类别:</td>
	    			<td>
	    			<select id="ntype" name="type" class="easyui-validatebox" data-options="required:true">
	    			<option value="">请选择</option>
	    			<option value="-1">教学管理</option>
	    			<option value="-2">系统管理</option>
	    			</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>URL:</td>
	    			<td><input class="easyui-validatebox" type="text" name="url" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>备注:</td>
	    			<td>
	    			<textarea name="remark" rows="6" cols="20"></textarea>
	    			</td>
	    		</tr>
				
				
</table>
<div style="padding:5px">
	    	
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	   	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    </div>

</form>
</div>


<div id="sw" class="easyui-window" title="查询窗口" align="center"
	data-options="modal:true,closed:true,iconCls:'icon-search',collapsible:false,minimizable:false" 
	style="width:400px;height:300px;padding:10px;">
	<form id="sf">
	<table>
	    		<tr>
	    			<td>权限名:</td>
	    			<td><input  type="text" id="con_nodeName" ></input></td>
	    		</tr>
	    		<tr>
	    			<td>类别:</td>
	    			<td>
	    			<select id="con_type" class="easyui-validatebox" data-options="required:true">
	    			<option value="0">全部</option>
	    			<option value="-1">教学管理</option>
	    			<option value="-2">系统管理</option>
	    			</select>
	    			</td>
	    		</tr>
				
</table>
</form>
 <div style="padding:5px">
	    	
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="f_clearSearch()">取消当前条件</a>
	   	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="f_search()">搜索</a>
	    </div>
	
</div>



<div id="lw" class="easyui-window" title="查看详情" align="center"
	data-options="modal:true,closed:true,iconCls:'icon-edit',collapsible:false,minimizable:false" 
	style="width:450px;height:350px;padding:10px;">
	<table>
	    		<tr>
	    			<td>权限名:</td>
	    			<td id="l_nodeName"></td>
	    		</tr>
	    		<tr>
	    			<td>类型:</td>
	    			<td id="l_type"></td>
	    		</tr>
	    		<tr>
	    			<td>URL:</td>
	    			<td id="l_url"></td>
	    		</tr>
	    		<tr>
	    			<td>备注:</td>
	    			<td id="l_remark"></td>
	    		</tr>
				
				
</table>

</div>


</body>
</html>

<script>
		$.extend($.fn.validatebox.defaults.rules, {
			md: {
				validator: function(value, param){
					
					return value.match(/^\d{4}-\d{2}-\d{2}$/);
				},
				message: '日期格式必须为： {0}格式。'
			}
		})
	</script>

