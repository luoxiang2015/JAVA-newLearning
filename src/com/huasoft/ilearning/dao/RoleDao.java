package com.huasoft.ilearning.dao;

import java.util.List;
import java.util.Map;

import com.huasoft.ilearning.bean.Role;

public interface RoleDao {
	/**
	 * ����
	 * @param depart
	 */
	public void save(Role role);
	
	public void update(Role role);
	
	/**
	 * ����id��ö���
	 * @param id
	 * @return
	 */
	public Role getRoleById(int id);
	
	/**
	 * ɾ��
	 * @param id
	 */
	public void delete(int id);
	
	public List<Role> queryAllByPage(String hql,int curpage,int pageSize,List<Object> params);
	public int getTotal(String hql,List<Object> params);
	/**
	 * ��ѯ����Ȩ��������Ȩ
	 * @return
	 */
	public List<Map<String,Object>> getNodes();
	
	public List<Object[]> getRolesByIds(String ids);
	
	

}
