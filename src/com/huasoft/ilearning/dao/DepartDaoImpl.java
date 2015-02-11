package com.huasoft.ilearning.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.huasoft.ilearning.bean.Depart;
import com.huasoft.ilearning.util.HibernateTool;

public class DepartDaoImpl implements DepartDao {
	
	private SessionFactory sessionFactory;
	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void saveOrUpdate(Depart depart) {
		//获取session
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(depart);//持久化操作
	}

	@Override
	public List<Depart> queryAll() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Depart");
		List<Depart> results=query.list();
		return results;
	}

	@Override
	public Depart getDepartById(int id) {
		Session session=sessionFactory.getCurrentSession();
		Depart d=(Depart) session.get(Depart.class, id);
		return d;
	}

	@Override
	public void delete(int id) {
		
		Session session=sessionFactory.getCurrentSession();
		Depart d=(Depart) session.get(Depart.class, id);
		session.delete(d);
		
	}

}
