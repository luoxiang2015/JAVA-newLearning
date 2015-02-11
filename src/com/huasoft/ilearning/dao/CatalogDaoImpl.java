package com.huasoft.ilearning.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.huasoft.ilearning.bean.Catalog;
import com.huasoft.ilearning.util.HibernateTool;

public class CatalogDaoImpl implements CatalogDao {
	
	private SessionFactory sessionFactory;
	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void save(Catalog catalog) {
		//获取session
		Session session=sessionFactory.getCurrentSession();
		
		session.save(catalog);//持久化操作
		Catalog p=(Catalog) session.get(Catalog.class, catalog.getPid());
		if(!p.getParent()){//添加新标记时检查父标记的isparent是否为true
			p.setParent(true);
		}
		
	}
	public void update(Catalog catalog){
		//获取session
		Session session=sessionFactory.getCurrentSession();
		session.update(catalog);//持久化操作
	}

	@Override
	public List<Catalog> queryByPid(int id) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Catalog where pid="+id);
		List<Catalog> results=query.list();
		return results;
	}

	@Override
	public Catalog getCatalogById(int id) {
		Session session=sessionFactory.getCurrentSession();
		Catalog d=(Catalog) session.get(Catalog.class, id);
		return d;
	}
	
	public String getMaxCode(int id){
		
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("select max(code) from Catalog where pid="+id);
		List<String> results=query.list();
		if(results.size()>0){
			return results.get(0);
		}
		return null;
	}

	@Override
	public void delete(int id) {

		Session session = sessionFactory.getCurrentSession();
		Catalog d = (Catalog) session.get(Catalog.class, id);
		int pid = d.getPid();
		session.delete(d);
		Query query = session
				.createQuery("select count(id) from Catalog where pid=" + pid);
		List<Integer> results = query.list();
		int count = results.get(0);
		if (count == 0) {
			Catalog p = (Catalog) session.get(Catalog.class, pid);
			p.setParent(false);
		}

	}

}
