package com.huasoft.ilearning.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.huasoft.ilearning.bean.Employee;
import com.huasoft.ilearning.dao.EmployeeDao;
import com.huasoft.ilearning.util.Page;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDao dao;

	public void setDao(EmployeeDao dao) {
		this.dao = dao;
	}

	@Override
	public void delete(int id) {

		dao.delete(id);
	}
	
	public Employee delete(int id,int page,Map<String,Object> params){
		dao.delete(id);
		StringBuilder hql=new StringBuilder("from Employee where 1=1 ");
		List<Object> values=null;
		if(params!=null&&params.size()>0){
			values=new ArrayList<Object>();
			Set<String> set=params.keySet();
			for(String name:set){
				
				Class<?> c=params.get(name).getClass();
				hql.append(" and ");
				if(c==String.class){
					hql.append(name);
					hql.append(" like ? ");
					values.add(params.get("%"+name+"%"));
				}else if(c==Integer.class||c==Date.class){
					hql.append(name);
					hql.append(" = ? ");
					values.add(params.get(name));
				}
			}
		}
		hql.append(" order by id desc");
		//查出下一页第一个数据
		List<Employee> list=dao.queryAllByPage(hql.toString(),page, 1,values);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Employee getById(int id) {
		return dao.getEmployeeById(id);
	}

	@Override
	public void insert(Employee employee) {
		employee.setCreateDate(new Date());
		dao.save(employee);
	}

	@Override
	public Page<Employee> queryAllByPage(int curpage, int pageSize,Map<String,Object> params) {
		Page<Employee> page=new Page<Employee>();
		StringBuilder hql=new StringBuilder("from Employee where 1=1 ");
		List<Object> values=null;
		if(params!=null&&params.size()>0){
			values=new ArrayList<Object>();
			Set<String> set=params.keySet();
			for(String name:set){
				values.add(params.get(name));
				Class<?> c=params.get(name).getClass();
				hql.append(" and ");
				if(c==String.class){
					hql.append(name);
					hql.append(" like %?% ");
				}else if(c==Integer.class||c==Date.class){
					hql.append(name);
					hql.append(" = ? ");
				}
			}
		}
		
		page.setRows(dao.queryAllByPage(hql.toString(),curpage, pageSize,values));
		page.setTotal(dao.getTotal("select count(*) "+hql.toString(),values));
		return page;
	}

	@Override
	public void update(Employee employee) {
		Employee e=dao.getEmployeeById(employee.getId());
		e.setBirthDay(employee.getBirthDay());
		e.setRemark(employee.getRemark());
		e.setSex(employee.getSex());
		e.setTruename(employee.getTruename());
		e.setUsername(employee.getUsername());
		e.setPwd(employee.getPwd());
		//dao.update(employee);
	}
	
	public List<Map<String,Object>> getRoles(int id){
		List<Map<String,Object>> res=dao.getRoles();
		Employee e=dao.getEmployeeById(id);
		String r=e.getRoles();
		if(r!=null&&(!r.equals(""))){
			r=","+r+",";
			for(int i=0;i<res.size();i++){
				Map<String,Object> temp=res.get(i);
				if(r.contains(","+temp.get("id")+",")){
					temp.put("checked", true);
				}
			}
		}
		return res;
	}
	
	public void updateRole(int id,String roles){
		Employee e=dao.getEmployeeById(id);
		e.setRoles(roles);
	}
	
	public Employee CheckEmployee(String username,String pwd){
		return dao.searchEmployee(username, pwd);
	}
	
	public List<Object[]> getNodesByUserId(int id){
		
		String roles=dao.getEmployeeById(id).getRoles();
		if(roles==null||roles.trim().equals("")){
			return null;
		}
		
		System.out.println(roles);
		
		return dao.getRolesByIds(roles);
	}
	

}
