package com.huasoft.ilearning.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huasoft.ilearning.bean.Depart;
import com.huasoft.ilearning.service.DepartService;
import com.huasoft.ilearning.service.DepartServiceImpl;
import com.huasoft.ilearning.util.JsonUtil;
import com.opensymphony.xwork2.ModelDriven;

public class DepartAction implements ModelDriven<Depart>{
	
	private Depart depart=new Depart();
	private DepartService service;
	
	public void setService(DepartService service) {
		this.service = service;
	}
	//添加或修改

	public String execute() throws IOException{
		
		service.insertOrUpdate(depart);
		
		HttpServletResponse resp=ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out=resp.getWriter();
		String res=JsonUtil.toJson(depart);
		out.print(res);
		System.out.println(res);
		return null;
	}
	//修改前加载数据
	public String load() throws IOException{
		HttpServletResponse resp=ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out=resp.getWriter();
		String res=JsonUtil.toJson(service.getById(depart.getId()));
		out.print(res);
		return null;
	}
	//查询所有数据
	public String query() throws IOException{
		List<Depart> datas=service.query();
		String res=JsonUtil.toJson(datas);
		//System.out.println(res);
		HttpServletResponse resp=ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out=resp.getWriter();
		System.out.println("hello");
		out.print(res);
		return null;
	}
	//删除数据
	public String delete() throws IOException{
		service.delete(depart.getId());
		HttpServletResponse resp=ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out=resp.getWriter();
		out.print("1");
		return null;
	}
	
	

	@Override
	public Depart getModel() {
		// TODO Auto-generated method stub
		return depart;
	}
	
	
	
	

}
