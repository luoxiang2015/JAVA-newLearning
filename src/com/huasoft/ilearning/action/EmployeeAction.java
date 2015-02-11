package com.huasoft.ilearning.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.huasoft.ilearning.bean.Employee;
import com.huasoft.ilearning.service.EmployeeService;
import com.huasoft.ilearning.util.JsonUtil;
import com.huasoft.ilearning.util.Page;
import com.huasoft.ilearning.util.UnicodeConverter;
import com.opensymphony.xwork2.ModelDriven;

public class EmployeeAction extends BaseAction implements ModelDriven<Employee>{
	
	private Employee employee=new Employee();
	private EmployeeService service;
	private int page=1;
	private int rows=10;
	private boolean hasNext;
	
	private List<Object[]> nodes;

	public List<Object[]> getNodes() {
		return nodes;
	}

	public void setNodes(List<Object[]> nodes) {
		this.nodes = nodes;
	}

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

	

	public void setService(EmployeeService service) {
		this.service = service;
	}
	
	public String add() throws IOException{
		try {
			service.insert(employee);
			String res=JsonUtil.toJson2(employee);
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
				if(employee.getBirthDay()!=null){
					params.put("birthDay", employee.getBirthDay());
				}
				if(employee.getSex()!=0){
					params.put("sex", employee.getSex());
				}
				if(employee.getUsername()!=null&&(!"".equals(employee.getUsername()))){
					params.put("username", employee.getUsername());
				}
				if(employee.getTruename()!=null&&(!"".equals(employee.getTruename()))){
					params.put("truename", employee.getTruename());
				}
				employee =service.delete(employee.getId(), page, params);
				if(employee==null){
					getOuter().print("null");
					return null;
				}
				String res=JsonUtil.toJson2(employee);
				res=UnicodeConverter.toEncodedUnicode(res, false);
				getOuter().print(res);
				return null;
			}
			service.delete(employee.getId());
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
			employee=service.getById(employee.getId());
			String res=JsonUtil.toJson(employee);
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
			service.update(employee);
			String res=JsonUtil.toJson2(employee);
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
		if(employee.getBirthDay()!=null){
			params.put("birthDay", employee.getBirthDay());
		}
		if(employee.getSex()!=0){
			params.put("sex", employee.getSex());
		}
		if(employee.getUsername()!=null&&(!"".equals(employee.getUsername()))){
			params.put("username", employee.getUsername());
		}
		if(employee.getTruename()!=null&&(!"".equals(employee.getTruename()))){
			params.put("truename", employee.getTruename());
		}//select * from lecture where   limit 0,10
		try {
			Page<Employee> p=service.queryAllByPage(page, rows,params);
			System.out.println(p.getRows());
			String res=JsonUtil.toJson3(p);
			getOuter().print(res);
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
			getOuter().print("error");
		}
		return null;
	}
	
	public String getRoles() throws IOException{
		try {
			String res=JsonUtil.toJson(service.getRoles(employee.getId()));
			res=UnicodeConverter.toEncodedUnicode(res, false);
			getOuter().print(res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			getOuter().print("error");
		}
		return null;
	}
	
	public String grant() throws IOException{
		try {
			service.updateRole(employee.getId(), employee.getRoles());
			getOuter().print("ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			getOuter().print("error");
		}
		return null;
	}
	
	public String login() throws IOException{
		try {
			employee=service.CheckEmployee(employee.getUsername(), employee.getPwd());
			if(employee!=null){
				ServletActionContext.getRequest().getSession().setAttribute("curUser", employee);
				
				nodes=service.getNodesByUserId(employee.getId());
				System.out.println(nodes);
				return "success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServletActionContext.getRequest().setAttribute("error","登录失败");
		return "error";
	}


	@Override
	public Employee getModel() {
		// TODO Auto-generated method stub
		return employee;
	}

}
