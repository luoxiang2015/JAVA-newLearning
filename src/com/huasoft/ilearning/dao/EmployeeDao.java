package com.huasoft.ilearning.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.huasoft.ilearning.bean.Employee;

public interface EmployeeDao {
	/**
	 * ����
	 * @param depart
	 */
	public void save(Employee employee);
	
	public void update(Employee employee);
	
	/**
	 * ����id��ö���
	 * @param id
	 * @return
	 */
	public Employee getEmployeeById(int id);
	
	/**
	 * ɾ��
	 * @param id
	 */
	public void delete(int id);
	
	public List<Employee> queryAllByPage(String hql,int curpage,int pageSize,List<Object> params);
	public int getTotal(String hql,List<Object> params);
	/**
	 * ��ѯ��ɫ�������Ȩ
	 * @return
	 */
	public List<Map<String,Object>> getRoles();
	
	public Employee searchEmployee(String username,String pwd);
	
	
	public List<Object[]> getRolesByIds(String ids);
	

}
