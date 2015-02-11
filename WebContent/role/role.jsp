<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>角色列表</title>
<link rel="stylesheet" type="text/css" href="../css/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="../css/themes/icon.css"/>
<link rel="stylesheet" href="../css/zTreeStyle/zTreeStyle.css" type="text/css"/>
	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="../js/json.js"></script>
	<script type="text/javascript" src="../js/jquery.ztree.all-3.5.min.js"></script>
	
	<script>
	function f_formate(value,rowdata,index){
			
			return "<a onclick=\"f_show("+rowdata.id+")\">查看</a>&nbsp;&nbsp;<a onclick=\"f_update("+rowdata.id+","+index+")\">修改</a>&nbsp;&nbsp;<a onclick=\"f_delete("+rowdata.id+")\">删除</a>&nbsp;&nbsp;<a onclick=\"f_grant("+rowdata.id+")\">授权</a>";
	}
	//格式化日期
	function myformatter(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
		}
	//添加按钮
	var curindex=-1;
	function f_add(){
		curindex=-1;
		$('#w').window({title:"添加新角色"});
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
				"roleName":$("#con_roleName").val(),
				"beginDate":$("#con_begin").val(),
				"endDate":$("#con_end").val()
			};
		$("#datalist").datagrid({ //执行搜索
			queryParams:searchparam,
			url:"roleAction!queryAll.action?"+new Date(), 
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
			url:"roleAction!queryAll.action?"+new Date(), 
			pageNumber:1    //从第一页开始                
		});
	}
	
	//查看
	function f_show(id){
		$.ajax({
			type: 'post',
	        // 处理请求的URI
	        url: 'roleAction!load.action?id='+id,
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
	        		$("#l_roleName").html(d.roleName);
	        		$("#l_createDate").html(d.createDate);
	        		$("#l_remark").html(d.remark);
	        		$('#lw').window('open');//
	        	}
	        }
	   });
	}
	
	//修改
	function f_update(id,index){
		curindex=index;//记录当前索引，以便修改
		$('#w').window({title:"修改角色"});
		$('#w').window('open');
		$("#ff").form("load","roleAction!load.action?id="+id+"&"+new Date());
		
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
	        url: 'roleAction!delete.action?id='+id+"&hasNext="+hasNext+"&page="+page,
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
	
	var setting = {
			check: {
				enable: true
				
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
	
	
	//授权
	var curTreeNodeId=0;
	function f_grant(id){
		curTreeNodeId=id;
		$('#gw').window('open');
		$.ajax({
			type: 'post',
	        // 处理请求的URI
	        url: 'roleAction!getNodes.action?id='+id+"&"+new Date(),
	        // 服务端返回的数据格式，一般是json,text,xml
	        dataType: 'text',
	        error: function() {
	        	$.messager.alert("提示","加载失败！");
	        },
	        success:function(data){
	        	if(data=='error'){
					$.messager.alert("提示","加载失败！");
					return ;
				}
	        	data=unescape(data);//转换Unicode编码
				var zNodes=JSON.parse(data);
	        	$.fn.zTree.init($("#grantTree"), setting, zNodes);
	        	
	        }
		});
	}
	
	//授权
	function f_submitGrant(){
		var zTree = $.fn.zTree.getZTreeObj("grantTree");
		var arr=zTree.getCheckedNodes(true);
		var res="";
		for(var i=0;i<arr.length;i++){
			//if(arr[i].id>0)
				res+=arr[i].id+",";
		}
		if(res.length>0){
			res=res.substring(0,res.length-1);
		}
		$('#gw').window('close');
		$.ajax({
			type: 'post',
	        // 处理请求的URI
	        url: 'roleAction!grant.action?id='+curTreeNodeId+"&nodes="+res+"&"+new Date(),
	        // 服务端返回的数据格式，一般是json,text,xml
	        dataType: 'text',
	        error: function() {
	        	$.messager.alert("提示","授权失败！");
	        },
	        success:function(data){
	        	if(data!='ok'){
					$.messager.alert("提示","授权失败！");
					return ;
				}
	        	$.messager.alert("提示","授权成功！");
	        	
	        }
		});
		
	}
	
	//提交表单
	function submitForm(){
		$('#w').window('close');//
		if(curindex==-1){//如果是添加
		$("#ff").form("submit",{
			url:"roleAction!add.action",
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
			url:"roleAction!update.action",
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
		}else{
			$("#ff").form("load","roleAction!load.action?id="+$("#eid").val()+"&"+new Date());
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
			
			}
		});//
		
	});
	
	
	</script>
	
</head>

<body>
<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="f_add()">新建</a>
<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="f_openSearch()">高级搜索</a>

	<table class="easyui-datagrid" title="Basic DataGrid" id="datalist" 
			data-options="url:'roleAction!queryAll.action',idField:'id',rownumbers:true,fitColumns:true,method:'post',noheader:true,singleSelect:true,pagination:true">
		<thead>
			<tr>
				<th data-options="field:'id',hidden:true,width:100">id</th>
				<th data-options="field:'roleName',width:100,align:'center'">角色名</th>
				<th data-options="field:'createDate',width:80,align:'center'">创建日期</th>
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
	    			<td>角色名:</td>
	    			<td><input class="easyui-validatebox" type="text" name="roleName" data-options="required:true"></input></td>
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
	    			<td>角色名:</td>
	    			<td><input  type="text" id="con_roleName" ></input></td>
	    		</tr>
	    		<tr>
	    			<td>开始日期:</td>
	    			<td>
	    			<input class="easyui-datebox" id="con_begin" data-options="formatter:myformatter" ></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>结束日期:</td>
	    			<td><input class="easyui-datebox" id="con_end" data-options="formatter:myformatter" ></input></td>
	    		</tr>
				
</table>
</form>
 <div style="padding:5px">
	    	
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="f_clearSearch()">取消当前条件</a>
	   	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="f_search()">搜索</a>
	    </div>
	
</div>



<div id="lw" class="easyui-window" title="查看详情" align="center"
	data-options="modal:true,closed:true,iconCls:'icon-search',collapsible:false,minimizable:false" 
	style="width:450px;height:350px;padding:10px;">
	<table>
	    		<tr>
	    			<td>角色名:</td>
	    			<td id="l_roleName"></td>
	    		</tr>
	    		<tr>
	    			<td>创建日期:</td>
	    			<td id="l_createDate"></td>
	    		</tr>
	    		<tr>
	    			<td>备注:</td>
	    			<td id="l_remark"></td>
	    		</tr>
				
				
</table>

</div>

<div id="gw" class="easyui-window" title="授权" align="center"
	data-options="modal:true,closed:true,iconCls:'icon-edit',collapsible:false,minimizable:false" 
	style="width:450px;height:350px;padding:10px;">
	
	<ul id="grantTree" class="ztree" style="float:left"></ul>
	<div style="padding:5px;float:right">
	    	
	   	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="f_submitGrant()">确定</a>
	</div>

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

