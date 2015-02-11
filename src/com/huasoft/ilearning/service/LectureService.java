package com.huasoft.ilearning.service;

import java.util.List;
import java.util.Map;

import com.huasoft.ilearning.bean.Lecture;

public interface LectureService {
	
	public void insert(Lecture lecture);
	
	public void update (Lecture lecture);
	
	public List<Map<String,String>> queryByCId(int id);
	
	public Lecture getById(int id);
	
	public void delete(int id);
	/**
	 * 重新分配大纲
	 * @param id
	 * @param catalog
	 */
	public void reSetCatalog(int id,int catalog);
	/**
	 * 把讲义改为未分配状态
	 * @param id
	 */
	public void deleteFromCatalog(int id);
	

}
