package com.huasoft.ilearning.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huasoft.ilearning.bean.Lecture;
import com.huasoft.ilearning.service.LectureService;
import com.huasoft.ilearning.service.LectureServiceImpl;
import com.huasoft.ilearning.util.JsonUtil;
import com.opensymphony.xwork2.ModelDriven;

public class LectureAction implements ModelDriven<Lecture> {
	
	private Lecture lecture=new Lecture();
	private LectureService service;
	
	public void setService(LectureService service) {
		this.service = service;
	}

	public String add() throws IOException{
		
		service.insert(lecture);
		HttpServletResponse resp=ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out=resp.getWriter();
		String res=JsonUtil.toJson2(lecture);
		out.print(res);
		return null;
	}
	
	public String query() throws IOException{
		List<Map<String,String>> list=service.queryByCId(lecture.getCatalog());
		HttpServletResponse resp=ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out=resp.getWriter();
		String res=JsonUtil.toJson(list);
		out.print(res);
		System.out.println(list);
		return null;
	}
	
	public String delete() throws IOException{
		service.delete(lecture.getId());
		HttpServletResponse resp=ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out=resp.getWriter();
		out.print("1");
		return null;
	}
	
	public String load() throws IOException{
		lecture=service.getById(lecture.getId());
		HttpServletResponse resp=ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out=resp.getWriter();
		String res=JsonUtil.toJson(lecture);
		out.print(res);
		System.out.println(res);
		return null;
	}
	
	public String update() throws IOException{
		service.update(lecture);
		HttpServletResponse resp=ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out=resp.getWriter();
		String res=JsonUtil.toJson2(lecture);
		out.print(res);
		return null;
	}
	
	public String resetCatalog() throws IOException{
		HttpServletResponse resp=ServletActionContext.getResponse();
		PrintWriter out=resp.getWriter();
		try {
			service.reSetCatalog(lecture.getId(), lecture.getCatalog());
			
			resp.setCharacterEncoding("UTF-8");
			
			out.print("1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("0");
		}
		return null;
	}
	
	public String changeCatalog() throws IOException{
		HttpServletResponse resp=ServletActionContext.getResponse();
		PrintWriter out=resp.getWriter();
		try {
			service.deleteFromCatalog(lecture.getId());
			out.print("1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("0");
		}
		
		return null;
	}
	

	@Override
	public Lecture getModel() {
		// TODO Auto-generated method stub
		return lecture;
	}

}
