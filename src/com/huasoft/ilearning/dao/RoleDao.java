package com.huasoft.ilearning.dao;

import java.util.List;
import java.util.Map;

import com.huasoft.ilearning.bean.Role;

public interface RoleDao {
	/**
	 * 保存
	 * @param depart
	 */
	public void save(Role role);
	
	public void update(Role role);
	
	/**
	 * 根据id获得对象
	 * @param id
	 * @return
	 */
	public Role getRoleById(int id);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(int id);
	
	public List<Role> queryAllByPage(String hql,int curpage,int pageSize,List<Object> params);
	public int getTotal(String hql,List<Object> params);
	/**
	 * 查询所以权限用于授权
	 * @return
	 */
	public List<Map<String,Object>> getNodes();
	
	public List<Object[]> getRolesByIds(String ids);
	
	

}
