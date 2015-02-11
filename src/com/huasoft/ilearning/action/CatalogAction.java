package com.huasoft.ilearning.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huasoft.ilearning.bean.Catalog;
import com.huasoft.ilearning.service.CatalogService;
import com.huasoft.ilearning.util.JsonUtil;
import com.opensymphony.xwork2.ModelDriven;

public class CatalogAction implements ModelDriven<Catalog>{
	
	private Catalog catalog=new Catalog();
	private CatalogService service;
	
	
	public void setService(CatalogService service) {
		this.service = service;
	}

	//添加或修改
	public String execute() throws IOException{
		
		
		try {
			service.insert(catalog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpServletResponse resp=ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out=resp.getWriter();
		String res=JsonUtil.toJson(catalog);
		out.print(res);
		System.out.println(res);
		return null;
	}
	
	public String query() throws IOException{
		
		List<Catalog> data=service.queryChildren(catalog.getId());
		HttpServletResponse resp=ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out=resp.getWriter();
		String res=JsonUtil.toJson(data);
		System.out.println(res);
		out.print(res);
		return null;
	}
	
	public String update() throws IOException{
		try {
			service.update(catalog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpServletResponse resp=ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out=resp.getWriter();
		String res=JsonUtil.toJson(catalog);
		out.print(res);
		System.out.println(res);
		return null;
	}
	
	public String delete() throws IOException{
		service.delete(catalog.getId());
		HttpServletResponse resp=ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out=resp.getWriter();
		out.print("1");
		return null;
	}
	
	public String load() throws IOException{
		Catalog c=service.getById(catalog.getId());
		HttpServletResponse resp=ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out=resp.getWriter();
		String res=JsonUtil.toJson(c);
		System.out.println(res);
		out.print(res);
		return null;
	}
	
	public String getCode() throws IOException{
		String code=service.getCode(catalog.getId(), catalog.getCode());
		HttpServletResponse resp=ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out=resp.getWriter();
		System.out.println(code);
		out.print(code);
		return null;
	}
	

	@Override
	public Catalog getModel() {
		// TODO Auto-generated method stub
		return catalog;
	}
	
	
	
	

}
