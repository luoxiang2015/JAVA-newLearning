package com.huasoft.ilearning.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.huasoft.ilearning.bean.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void delete(int id) {

		Session session=sessionFactory.getCurrentSession();
		session.delete(session.get(Employee.class, id));
		
	}

	@Override
	public Employee getEmployeeById(int id) {
		Session session=sessionFactory.getCurrentSession();
		
		return (Employee) session.get(Employee.class, id);
	}

	@Override
	public List<Employee> queryAllByPage(String hql,int curpage, int pageSize,List<Object> params) {
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if (params != null) {//根据参数动态生成hql
			query = session.createQuery(hql);
			for (int i=0;i<params.size();i++) {//给占位符赋值
				query.setParameter(i, params.get(i));
			}
		}
		int index=(curpage-1)*pageSize;
		query.setFirstResult(index);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public void save(Employee employee) {

		Session session=sessionFactory.getCurrentSession();
		session.save(employee);
	}

	@Override
	public void update(Employee employee) {

		Session session=sessionFactory.getCurrentSession();
		session.update(employee);
	}
	
	public int getTotal(String hql,List<Object> params){
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if (params != null) {
			for (int i=0;i<params.size();i++) {//给占位符赋值
				query.setParameter(i, params.get(i));
			}
		}
		int count=(Integer) query.list().get(0);
		return count;
	}
	
	public List<Map<String,Object>> getRoles(){
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery("select new map(id as id,roleName as name) from Role");
		return query.list();
	}
	
	public Employee searchEmployee(String username,String pwd){
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Employee where username=? and pwd=?");
		query.setString(0, username);
		query.setString(1, pwd);
		List<Employee> l=query.list();
		if(l.size()>0){
			return l.get(0);
		}
		return null;
	}
	
	public List<Object[]> getRolesByIds(String ids){
		//查询所有角色对应的权限id
		String hql="select nodes from Role where id in("+ids+")";
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<String> nodeids=query.list();
		String temp="";
		for(int i=0;i<nodeids.size();i++){
			temp+=nodeids.get(i)+",";
		}
		System.out.println(temp);
		temp=temp.substring(0, temp.length()-1);
		//查询所有权限信息
		hql="select name,type,url from Node where id in("+temp+")";
		query = session.createQuery(hql);
		List<Object[]> list =query.list();
		return list;
	}

}
