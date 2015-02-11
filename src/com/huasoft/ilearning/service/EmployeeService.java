package com.huasoft.ilearning.service;

import java.util.List;
import java.util.Map;

import com.huasoft.ilearning.bean.Employee;
import com.huasoft.ilearning.util.Page;

public interface EmployeeService {
	
	public void insert(Employee employee);
	
	public void update (Employee employee);
	
	public Page<Employee> queryAllByPage(int curpage,int pageSize,Map<String,Object> params);
	
	public Employee getById(int id);
	
	public void delete(int id);
	/**
	 * 删除后提取下一条补充
	 * @param id
	 * @param page
	 * @param params
	 * @return
	 */
	public Employee delete(int id,int page,Map<String,Object> params);
	
	public List<Map<String,Object>> getRoles(int id);
	/**
	 * 修改角色
	 * @param id
	 * @param roles
	 */
	public void updateRole(int id,String roles);
	
	public Employee CheckEmployee(String username,String pwd);
	public List<Object[]> getNodesByUserId(int id);
	

}
