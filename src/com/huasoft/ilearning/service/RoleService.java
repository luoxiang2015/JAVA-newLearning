package com.huasoft.ilearning.service;

import java.util.List;
import java.util.Map;

import com.huasoft.ilearning.bean.Role;
import com.huasoft.ilearning.util.Page;

public interface RoleService {
	
	public void insert(Role role);
	
	public void update (Role role);
	
	public Page<Role> queryAllByPage(int curpage,int pageSize,Map<String,Object> params);
	
	public Role getById(int id);
	
	public void delete(int id);
	/**
	 * 删除后提取下一条补充
	 * @param id
	 * @param page
	 * @param params
	 * @return
	 */
	public Role delete(int id,int page,Map<String,Object> params);
	
	public List<Map<String,Object>> getNodes(int id);
	/**
	 * 授权
	 * @param id
	 * @param nodes
	 */
	public void updateNodes(int id,String nodes);
	

}
