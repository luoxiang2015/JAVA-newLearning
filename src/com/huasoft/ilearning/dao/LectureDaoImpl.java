package com.huasoft.ilearning.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.huasoft.ilearning.bean.Lecture;
import com.huasoft.ilearning.util.HibernateTool;

public class LectureDaoImpl implements LectureDao {
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void save(Lecture lecture) {
		//获取session
		Session session=sessionFactory.getCurrentSession();
		session.save(lecture);//持久化操作
		
	}
	public void update(Lecture lecture){
		//获取session
		Session session=sessionFactory.getCurrentSession();
		session.update(lecture);//持久化操作
	}

	

	@Override
	public Lecture getLectureById(int id) {
		Session session=sessionFactory.getCurrentSession();
		Lecture d=(Lecture) session.get(Lecture.class, id);
		return d;
	}
	
	

	@Override
	public void delete(int id) {

		Session session = sessionFactory.getCurrentSession();
		Lecture d = (Lecture) session.get(Lecture.class, id);
		session.delete(d);
		

	}
	@Override
	public List<Map<String,String>> queryByCid(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query=null;
		//Objece[]  list
		try {//select new Lecture(id,title,creater,createrDate) from Lecture where catalog="+id
			query = session.createQuery("select new map(id as id,title as title,creater as creater,createDate as createDate) from Lecture where catalog="+id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Map<String,String>> res=query.list();
		return res;
	}
	
	
	
	
	public void updateCatalog(int catalog){
		Session session = sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Lecture where catalog="+catalog);
		List<Lecture> list=query.list();
		for(int i=0;i<list.size();i++){
			Lecture l=list.get(i);
			l.setCatalog(-2);//修改为未分配状态
		}
	}

}
