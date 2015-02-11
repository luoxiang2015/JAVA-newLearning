package com.huasoft.ilearning.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import com.huasoft.ilearning.bean.Role;
import com.huasoft.ilearning.service.RoleService;
import com.huasoft.ilearning.util.JsonUtil;
import com.huasoft.ilearning.util.Page;
import com.huasoft.ilearning.util.UnicodeConverter;
import com.opensymphony.xwork2.ModelDriven;

public class RoleAction extends BaseAction implements ModelDriven<Role>{
	
	private Role role=new Role();
	private RoleService service;
	private int page=1;
	private int rows=10;
	private boolean hasNext;
	private Date beginDate;
	private Date endDate;

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

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setService(RoleService service) {
		this.service = service;
	}
	
	public String add() throws IOException{
		try {
			service.insert(role);
			String res=JsonUtil.toJson2(role);
			res=UnicodeConverter.toEncodedUnicode(res, false);
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
				if(role.getRoleName()!=null&&(!"".equals(role.getRoleName()))){
					params.put("roleName", role.getRoleName());
				}
				if(beginDate!=null&&endDate!=null){
					params.put("beginDate", beginDate);
					params.put("endDate", endDate);
				}
				role =service.delete(role.getId(), page, params);
				if(role==null){
					getOuter().print("null");
					return null;
				}
				String res=JsonUtil.toJson2(role);
				res=UnicodeConverter.toEncodedUnicode(res, false);
				getOuter().print(res);
				return null;
			}
			service.delete(role.getId());
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
			role=service.getById(role.getId());
			String res=JsonUtil.toJson(role);
			getOuter().print(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			getOuter().print("error");
		}
		return null;
	}
	
	
	
	public String update() throws IOException{
		try {
			service.update(role);
			String res=JsonUtil.toJson2(role);
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
		if(role.getRoleName()!=null&&(!"".equals(role.getRoleName()))){
			params.put("roleName", role.getRoleName());
		}
		if(beginDate!=null&&endDate!=null){
			params.put("beginDate", beginDate);
			params.put("endDate", endDate);
		}
		try {
			Page<Role> p=service.queryAllByPage(page, rows,params);
			String res=JsonUtil.toJson3(p,new TypeToken<Page<Role>>() {}.getType());
			getOuter().print(res);
		} catch (Exception e) {
			e.printStackTrace();
			getOuter().print("error");
		}
		return null;
	}
	
	public String getNodes() throws IOException{
		try {
			List<Map<String,Object>> l=service.getNodes(role.getId());
			String res=JsonUtil.toJson(l);
			res=UnicodeConverter.toEncodedUnicode(res, false);
			getOuter().print(res);
			System.out.println(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			getOuter().print("error");
		}
		return null;
	}
	
	public String grant() throws IOException{
		try {
			service.updateNodes(role.getId(), role.getNodes());
			getOuter().print("ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			getOuter().print("error");
		}
		return null;
	}


	@Override
	public Role getModel() {
		// TODO Auto-generated method stub
		return role;
	}

}
