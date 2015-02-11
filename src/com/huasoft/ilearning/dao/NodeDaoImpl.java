package com.huasoft.ilearning.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.huasoft.ilearning.bean.Node;
import com.huasoft.ilearning.bean.Role;

public class NodeDaoImpl implements NodeDao {
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void delete(int id) {

		Session session=sessionFactory.getCurrentSession();
		session.delete(session.get(Node.class, id));
		
	}

	@Override
	public Node getNodeById(int id) {
		Session session=sessionFactory.getCurrentSession();
		
		return (Node) session.get(Node.class, id);
	}

	@Override
	public List<Node> queryAllByPage(String hql,int curpage, int pageSize,List<Object> params) {
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if (params != null) {
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
	public void save(Node node) {

		Session session=sessionFactory.getCurrentSession();
		session.save(node);
	}

	@Override
	public void update(Node node) {

		Session session=sessionFactory.getCurrentSession();
		session.update(node);
	}
	
	public int getTotal(String hql,List<Object> params){
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if (params != null) {
			query = session.createQuery(hql);
			for (int i=0;i<params.size();i++) {//给占位符赋值
				query.setParameter(i, params.get(i));
			}
		}
		int count=(Integer) query.list().get(0);
		return count;
	}

}
