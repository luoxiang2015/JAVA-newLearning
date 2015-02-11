package com.huasoft.ilearning.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.huasoft.ilearning.bean.Employee;

public interface EmployeeDao {
	/**
	 * 保存
	 * @param depart
	 */
	public void save(Employee employee);
	
	public void update(Employee employee);
	
	/**
	 * 根据id获得对象
	 * @param id
	 * @return
	 */
	public Employee getEmployeeById(int id);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(int id);
	
	public List<Employee> queryAllByPage(String hql,int curpage,int pageSize,List<Object> params);
	public int getTotal(String hql,List<Object> params);
	/**
	 * 查询角色，完成授权
	 * @return
	 */
	public List<Map<String,Object>> getRoles();
	
	public Employee searchEmployee(String username,String pwd);
	
	
	public List<Object[]> getRolesByIds(String ids);
	

}
