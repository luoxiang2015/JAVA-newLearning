<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Basic Layout - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="../css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../css/demo.css">
	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
	<script charset="utf-8" src="../js/kindeditor.js" ></script>
	
	<style type="text/css" >
	a{
	cursor:pointer;
	}
	 #float
        {
            position: absolute;
            width: 24px;
            height: 34px;
            z-index:999999;
            background-image: url(../imgs/loc.png);
            display: none;
        }
	</style>
	
	<link rel="stylesheet" href="../css/zTreeStyle/zTreeStyle.css" type="text/css">

	<script type="text/javascript" src="../js/jquery.ztree.core-3.5.min.js"></script>
	<SCRIPT type="text/javascript">
	
		var setting = {
				async: {
					enable: true,
					url:"catalogAction!query.action?a="+new Date(),
					autoParam:["id"]
				},
				callback: {
					onClick: f_clickTree
					
				},
			view: {
				selectedMulti: false
			}
		};
		
		var curNode=null;
		var resetid=0;//存储重置节点的id
		var resettitle="";
		var resetindex=-1;
		
		function f_clickTree(ev,treeId,treeNode){
			
			if(resetid!=0){//如果是在重新分配状态
				f_unbund();//取消鼠标跟踪
				if(treeNode.id==-2){
					$.messager.defaults={ok:"放弃",cancel:"继续"};
					$.messager.confirm('提示','该讲义已经是未分配状态，是否放弃重置操作？如果继续请选择新的大纲节点。',function(r){ //  
						if (r){  //如果放弃，清空重置
							resetid=0;
							resettitle="";
							resetindex=-1;
							$("#float").css("display", "none");
						}else{
							f_bund();
						}
					});
					return;
				}
				$.messager.defaults={ok:"是",cancel:"否"};
				$.messager.confirm('提示','是否将“'+resettitle+'”分配到'+treeNode.name+'节点下',function(r){ //  
					if (r){  //如果确定分配，发送请求修改该讲义的大纲
						$.get("lectureAction!resetCatalog.action?catalog="+treeNode.id+"&id="+resetid,function(data){
							if(parseInt(data)==1){
								
								$.messager.alert('提示','分配成功！'); 
								/*$('#datalist').datagrid({//重新加载数据表中的数据
									url:'lectureAction!query.action?catalog='+curNode.id+"&"+new Date()
								});
								$("#addbt").show();*/
								//删除数据表格中的行
								var ind=$("#datalist").datagrid("getRowIndex",resetid);
								$("#datalist").datagrid("deleteRow",ind);
								treeObj.selectNode(curNode,false);//重新选中未分配节点，保证节点和数据表格数据对应
								
							}else{
								$.messager.alert('提示','分配失败！'); 
							}
							resetid=0;
							resettitle="";
							resetindex=-1;
							$("#float").css("display", "none");
						});
					}else{
						f_bund();
					}
				});
				return ;
			}
			curNode=treeNode;
			if(treeNode.id==-2){//未分配中存储的是已删除的讲义，不能有添加功能
				$("#addbt").hide();
			}else{
				$("#addbt").show();
			}
			$('#datalist').datagrid({//
				url:'lectureAction!query.action?catalog='+curNode.id+"&"+new Date()
			});
		}
		

 
		var treeObj;
		$(document).ready(function(){
			treeObj=$.fn.zTree.init($("#treeDemo"), setting);

		});
		
		KE.show({
			id : 'content1'
			
		});

		
		function f_formate(value,rowdata,index){
			if(curNode.id==-2){
				return "<a onclick=\"f_reset("+rowdata.id+",'"+rowdata.title+"',"+index+")\">重新分配</a>&nbsp;&nbsp;<a onclick=\"f_delete("+rowdata.id+","+index+")\">删除</a>";
			}
			return "<a onclick=\"f_init("+rowdata.id+","+index+")\">修改</a>&nbsp;&nbsp;<a onclick=\"f_deleteFromCatalog("+rowdata.id+","+index+")\">删除</a>";
		}
		
		function f_delete(id,index){
			$.messager.defaults={ok:"是",cancel:"否"};
			$.messager.confirm('提示','是否确定删除？，删除后将无法恢复。',function(r){ //
				if (r){  //如果确定删除，删除数据库的同时更新数据表
					$.get("lectureAction!delete.action?id="+id,function(data){
						if(parseInt(data)==1){
							index=$("#datalist").datagrid("getRowIndex",id);
							$("#datalist").datagrid("deleteRow",index);
						}
							
					});
				}
			});
			
		}
		
		function f_deleteFromCatalog(id,index){
			$.messager.defaults={ok:"是",cancel:"否"};
			$.messager.confirm('提示','是否删除讲义？<br>删除后该讲义将会被存入未分配节点下。',function(r){ //
				if (r){  //如果确定删除，将讲义状态修改为未分配 
					$.get("lectureAction!changeCatalog.action?id="+id,function(data){
						if(parseInt(data)==1){
							//$("#datalist").datagrid("deleteRow",index);
							index=$("#datalist").datagrid("getRowIndex",id);
							$("#datalist").datagrid("deleteRow",index);
						}
						else{
							$.messager.alert('提示','删除失败！');  
						}
					});
				}
			});
			
		}
		
		var curindex=-1;
		var curid=0;
		function f_init(id,index){
			curindex=index;
			curid=id;
			
			$("#ff").form({
				
				onLoadSuccess:function(data){
					//alert(data.content);
					KE.util.setFullHtml('content1', '');
					KE.util.focus("content1");
			          KE.util.selection("content1");
			          KE.util.insertHtml("content1", data.content);

				}
			});
			
			$("#ff").form("load","lectureAction!load.action?id="+id+"&"+new Date());
			
			$('#w').window({title:'修改讲义'});
			$('#w').window('open');
		}
		
		
		function f_add(){
			curindex=-1;
			curid=0;
			if(curNode){
				$('#w').window({title:'在“'+curNode.name+'”下新增讲义'});
				$('#w').window('open');
				$('#ff').form('clear');
				 KE.util.setFullHtml('content1', '');

			}else{
				$.messager.alert('提示','请先选择大纲！');  
			}
			$('#datalist').datagrid("unselectAll");
			
		}
		
		
		//重新分配讲义所属大纲
		function f_reset(id,title,index){
			resettitle=title;
			resetid=id;
			resetindex=index;
			$.messager.alert('提示','请在左边大纲树上选择要分配到哪个大纲节点。');  
			f_bund();
		}
		
		
		
		function submitForm(){//
			if(curindex==-1){
				$("#ff").form("submit",{
					url:"lectureAction!add.action?catalog="+curNode.id,
					success:function(data){//
						var d=JSON.parse(data); //
						
						$('#w').window('close');//
						
						$("#datalist").datagrid("appendRow",d);//
						
					}
					
				});
			}else{
				$("#ff").form("submit",{
					url:"lectureAction!update.action?catalog="+curNode.id,
					success:function(data){//
						var d=JSON.parse(data); //
						
						$('#w').window('close');//
						
						$("#datalist").datagrid("updateRow",{index:curindex,row:d});//
					}
					
				});
			}
			
		}
		
		function clearForm(){
			if(curid==0){
				$.messager.confirm('提示','是否确定清除？',function(r){ //
					if (r){  //
						$('#ff').form('clear');
						KE.util.setFullHtml('content1', '');
					}
				});
			}else{
				
				$("#ff").form("load","lectureAction!load.action?id="+curid+"&"+new Date());
			}
			
		}
		//刷新大纲树
		function f_reflush(){
			treeObj.reAsyncChildNodes(null, "refresh");
			var data=$('#datalist').datagrid("getRows");
			var len=data.length;
			for(var i=0;i<len;i++){//
				$('#datalist').datagrid("deleteRow",0);
			}
		}
		//设置鼠标跟踪
		function f_bund(){
			$("#float").css("display", "block");
			$(document).bind("mousemove",function(e) {
				$("#float").css("left", (e.pageX-24)).css("top", (e.pageY-34));
			            });
		}
		//取消鼠标跟踪
		function f_unbund(){
			//$("#float").css("display", "none");
			$(document).unbind("mousemove");
		}
		
		</script>
	
	
	
</head>
<body class="easyui-layout">
	
	<div id="float">
       
    </div>
		
		
		<div data-options="region:'west',split:true" style="width:200px;">
		<a href="#" onclick="f_reflush()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新大纲数</a>
		
		<ul id="treeDemo" class="ztree"></ul>
		
		
		</div>
		<div data-options="region:'center'">
		<a id="addbt" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="f_add()">新增</a>
			<table class="easyui-datagrid" id="datalist"
					data-options="idField:'id',method:'post',border:false,singleSelect:true,fit:true,fitColumns:true,rownumbers:true">
				<thead>
					<tr>
						<th data-options="field:'id',hidden:true" width="80">id</th>
						<th data-options="field:'title'" width="80">主题</th>
						<th data-options="field:'creater'" width="100">作者</th>
						<th data-options="field:'createDate'" width="80">添加时间</th>
						<th data-options="field:'attr',formatter:f_formate" width="150">操作</th>
					</tr>
				</thead>
			</table>
		</div>
		
		
		
		<div id="w" class="easyui-window" title="Modal Window" 
	data-options="modal:true,closed:true,iconCls:'icon-edit',collapsible:false,minimizable:false" 
	style="width:760px;height:360px;padding:10px;">
		<form id="ff" name="ff"  method="post">
		<input type="hidden" id="id" name="id"/>
<table style="width:100%">
	    		<tr>
	    			<td>主题：</td>
	    			<td><input class="easyui-validatebox" type="text" id="title" name="title" data-options="required:true"></input></td>
	    			<td>作者：</td>
	    			<td><input class="easyui-validatebox" type="text" id="creater" name="creater" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td colspan="4">
					<textarea  id="content1" name="content" style="width:700px;height:230px;"></textarea>
					</td>
	    			
	    		</tr>
	    		
				
				
</table>
<div style="padding:5px">
	    	
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">reset</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a>
	    </div>

</form>
</div>
	
</body>
</html>