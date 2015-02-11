package com.huasoft.ilearning.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import com.huasoft.ilearning.bean.Node;
import com.huasoft.ilearning.service.NodeService;
import com.huasoft.ilearning.util.JsonUtil;
import com.huasoft.ilearning.util.Page;
import com.huasoft.ilearning.util.UnicodeConverter;
import com.opensymphony.xwork2.ModelDriven;

public class NodeAction extends BaseAction implements ModelDriven<Node>{
	
	private Node node=new Node();
	private NodeService service;
	private int page=1;
	private int rows=10;
	private boolean hasNext;

	public boolean getHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}


	public void setService(NodeService service) {
		this.service = service;
	}
	
	public String add() throws IOException{
		try {
			service.insert(node);
			String res=JsonUtil.toJson2(node);
			res=UnicodeConverter.toEncodedUnicode(res, false);
			System.out.println(res);
			getOuter().print(res);
		} catch (Exception e) {
			e.printStackTrace();
			getOuter().print("error");
		}
		return null;
	}
	
	public String delete() throws IOException{
		try {
			if(hasNext){
				Map<String,Object> params=new HashMap<String,Object>();
				if(node.getName()!=null&&(!"".equals(node.getName()))){
					params.put("nodeName", node.getName());
				}
				if(node.getType()!=0){
					params.put("type", node.getType());
				}
				
				String res=JsonUtil.toJson2(node);
				res=UnicodeConverter.toEncodedUnicode(res, false);
				getOuter().print(res);
				return null;
			}
			service.delete(node.getId());
			getOuter().print("ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			getOuter().print("error");
		}
		return null;
	}
	
	public String load() throws IOException{
		try {
			node=service.getById(node.getId());
			String res=JsonUtil.toJson(node);
			getOuter().print(res);
			System.out.println(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			getOuter().print("error");
		}
		return null;
	}
	
	
	
	public String update() throws IOException{
		try {
			service.update(node);
			String res=JsonUtil.toJson2(node);
			res=UnicodeConverter.toEncodedUnicode(res, false);
			getOuter().print(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			getOuter().print("error");
		}
		return null;
	}
	
	public String queryAll() throws IOException{
		Map<String,Object> params=new HashMap<String,Object>();
		if(node.getName()!=null&&(!"".equals(node.getName()))){
			params.put("nodeName", node.getName());
		}
		if(node.getType()!=0){
			params.put("type", node.getType());
		}
		try {
			Page<Node> p=service.queryAllByPage(page, rows,params);
			System.out.println(p.getRows());
			String res=JsonUtil.toJson3(p,new TypeToken<Page<Node>>() {}.getType());
			getOuter().print(res);
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
			getOuter().print("error");
		}
		return null;
	}


	@Override
	public Node getModel() {
		// TODO Auto-generated method stub
		return node;
	}

}
