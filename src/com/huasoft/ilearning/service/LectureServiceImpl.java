package com.huasoft.ilearning.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.huasoft.ilearning.bean.Lecture;
import com.huasoft.ilearning.dao.LectureDao;
import com.huasoft.ilearning.dao.LectureDaoImpl;

public class LectureServiceImpl implements LectureService {

	private LectureDao dao;
	
	public void setDao(LectureDao dao) {
		this.dao = dao;
	}



	@Override
	public void insert(Lecture lecture) {
		lecture.setCreateDate(new Date(System.currentTimeMillis()).toString());
		dao.save(lecture);
		

	}

	

	@Override
	public Lecture getById(int id) {
		return dao.getLectureById(id);
	}

	@Override
	public void delete(int id) {
		
		dao.delete(id);
		
	}
	
	
	public void deleteFromCatalog(int id){
		Lecture temp=dao.getLectureById(id);
		temp.setCatalog(-2);
		dao.update(temp);
	}
	

	@Override
	public void update(Lecture lecture) {
		Lecture temp=dao.getLectureById(lecture.getId());
		temp.setTitle(lecture.getTitle());
		temp.setContent(lecture.getContent());
		temp.setCreater(lecture.getCreater());
		dao.update(temp);
	}



	@Override
	public List<Map<String,String>> queryByCId(int id) {
		
		return dao.queryByCid(id);
	}
	
	
	public void reSetCatalog(int id,int catalog){
		
		Lecture temp=dao.getLectureById(id);
		temp.setCatalog(catalog);
		dao.update(temp);
		
	}

}
