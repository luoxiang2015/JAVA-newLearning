package com.huasoft.ilearning.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.huasoft.ilearning.bean.Role;

public class RoleDaoImpl implements RoleDao {
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void delete(int id) {

		Session session=sessionFactory.getCurrentSession();
		session.delete(session.get(Role.class, id));
		
	}

	@Override
	public Role getRoleById(int id) {
		Session session=sessionFactory.getCurrentSession();
		
		return (Role) session.get(Role.class, id);
	}

	@Override
	public List<Role> queryAllByPage(String hql,int curpage, int pageSize,List<Object> params) {
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if (params != null) {
			query = session.createQuery(hql);
			for (int i=0;i<params.size();i++) {//缁欏崰浣嶇璧嬪�
				query.setParameter(i, params.get(i));
			}
		}
		int index=(curpage-1)*pageSize;
		query.setFirstResult(index);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public void save(Role role) {

		Session session=sessionFactory.getCurrentSession();
		session.save(role);
	}

	@Override
	public void update(Role role) {

		Session session=sessionFactory.getCurrentSession();
		session.update(role);
	}
	
	public int getTotal(String hql,List<Object> params){
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if (params != null) {
			query = session.createQuery(hql);
			for (int i=0;i<params.size();i++) {//缁欏崰浣嶇璧嬪�
				query.setParameter(i, params.get(i));
			}
		}
		int count=(Integer) query.list().get(0);
		return count;
	}
	
	public List<Map<String,Object>> getNodes(){
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery("select new map(id as id,type as pId,name as name) from Node");
		List<Map<String,Object>> res=query.list();
		Map<String,Object> temp=new HashMap<String,Object>();
		temp.put("id", -1);
		temp.put("pId", "0");
		temp.put("name", "教学管理");
		temp.put("open", true);
		res.add(temp);
		temp=new HashMap<String,Object>();
		temp.put("id", -2);
		temp.put("pId", "0");
		temp.put("name", "系统管理");
		temp.put("open", true);
		res.add(temp);
		return res;
		
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
		temp=temp.substring(0, temp.length()-1);
		//查询所有权限信息
		hql="select name,type,url from Node where id in("+temp+")";
		query = session.createQuery(hql);
		List<Object[]> list =query.list();
		return list;
	}

}
