<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="../css/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="../css/themes/icon.css"/>
	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../js/json.js"></script>
	<link rel="stylesheet" href="../css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	</style>

	<script type="text/javascript" src="../js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript">
		var setting = {
				edit: {
					enable: true,
					showRemoveBtn: f_showRemove,
					showRenameBtn: f_showRename,
					renameTitle:'修改',
					removeTitle:'删除'
				},
				async: {
					enable: true,
					url:"catalogAction!query.action?a="+new Date(),
					autoParam:["id"]
				},
			callback: {
				
				beforeRemove:f_remove,
				beforeEditName:f_openEdit
			},
			view:{
				dblClickExpand :false ,
				addHoverDom: addHoverDom,
				removeHoverDom: removeHoverDom

			}

		};
		
		function addHoverDom(treeId, treeNode) {
			if(treeNode.id==-2){
				return false;
			}
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
				+ "' title='添加' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addBtn_"+treeNode.tId);
			if (btn) btn.bind("click", function(){
				$('#fu').form('clear');
				$.get("catalogAction!getCode.action?id="+treeNode.id+"&code="+treeNode.code,function(data){
	   				$("#code").val(data);
	   			});
				curNode=treeNode;
				var t="在“"+treeNode.name+"”下添加新节点";
				$('#w').window({title:t});
				$('#w').window('open');
				curNode=treeNode;
				return false;
			});
		};
		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_"+treeNode.tId).unbind().remove();
		};

		
		function f_showRemove(treeId, treeNode){
			if(treeNode.id==-2){
				return false;
			}
			return !treeNode.isParent;
		}
		
		function f_showRename(treeid,treeNode){
			if(treeNode.id==-2){
				return false;
			}
			return treeNode.id!=-1;
		}
		
		function f_openEdit(treeid,treeNode){
			curNode=treeNode;
			$('#fu').form('clear');
   			$("#fu").form("load","catalogAction!load.action?id="+treeNode.id+"&"+new Date());
   			var t="修改“"+treeNode.name+"”节点";
			$('#w').window({title:t});
   			$('#w').window('open');
   			return false;
		}
		
		function f_remove(treeid,treeNode){
			$.messager.confirm('确认删除','是否确定删除？<br>删除后该大纲下的讲义将会被转存到“未分配”选项下。',function(r){ //弹出确认框  
				if(r){
					$.get("catalogAction!delete.action?id="+treeNode.id,function(data){
						treeObj.removeNode(treeNode,false);
					});
				}
			});
			return false;
		}
		var curNode=null;
		
		var treeObj;
 
		$(document).ready(function(){
			treeObj=$.fn.zTree.init($("#treeDemo"), setting);
		});
		
		
		
		function f_update(){
			if($("#id").val()==''){
				if($("#code").val()==""){
					$.messager.alert('提示','未生成编号，请重试！'); 
					$('#w').window('close');
					return ;
				}
				
				$("#pid").val(curNode.id);
				
				$("#fu").form("submit",{
					url:"catalogAction.action",
					success:function(data){//服务器成功返回时回调方法
						var d=JSON.parse(data); //把服务器返回的数据转换成json对象
						$('#fu').form('clear');
						$('#w').window('close');
						treeObj.addNodes(curNode,d);
						treeObj.cancelSelectedNode();

					}
					
				});
			}else{
				$("#fu").form("submit",{
					url:"catalogAction!update.action",
					success:function(data){//服务器成功返回时回调方法
						var d=JSON.parse(data); //把服务器返回的数据转换成json对象
						$('#fu').form('clear');
						$('#w').window('close');
						curNode.name=d.name;
						curNode.remark=d.remark;
						treeObj.updateNode(curNode);
						treeObj.cancelSelectedNode();
					}
					
				});
				
			}
			
		}
		
		function f_clear(){
			if($("#id").val()==''){//如果是添加
				$("#name").val("");
				$("#remark").val("");
				$("#name").focus();
			}else{
				$("#fu").form("load","catalogAction!load.action?id="+$('#id').val()+"&"+new Date());
			}
		}

	</SCRIPT>


</head>

<body>

鼠标经过时显示节点编辑按钮，父节点不可删除：

<ul id="treeDemo" class="ztree"></ul>


<div id="w" class="easyui-window" title="编辑当前节点" align="center"
	data-options="modal:true,closed:true,iconCls:'icon-edit',collapsible:false,minimizable:false" 
	style="width:400px;height:260px; ">
	
	
		<form id="fu" method="post">
		<input id="id" type="hidden" name="id" value=''/>
		<input id="pid" type="hidden" name="pid"/>
		<br><br>
		<table>
		<tr>
		<td>编号：</td>
		<td><input type="text" id="code" name="code" readonly="readonly"/></td>
		</tr>
		<tr>
		<td>名称：</td>
		<td><input id="name" type="text" id="name" name="name"  class="easyui-validatebox"data-options="required:true"/></td>
		</tr>
		<tr>
		<td>备注：</td>
		<td><textarea id="remark" name="remark"></textarea></td>
		</tr>
		</table>
		
		
		<div style="padding:5px">
	    	
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="f_clear()">Clear</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="f_update()">保存</a>
	    </div>
		</form>
		
	
	
	
</div>


</body>
</html>
