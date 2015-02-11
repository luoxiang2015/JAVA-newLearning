package com.huasoft.ilearning.dao;

import java.util.List;
import java.util.Map;

import com.huasoft.ilearning.bean.Lecture;

public interface LectureDao {
	/**
	 * 保存数据
	 * @param 
	 */
	public void save(Lecture lecture);
	
	public void update(Lecture lecture);
	
	/**
	 * 通过id查找数据
	 * @param id
	 * @return
	 */
	public Lecture getLectureById(int id);
	
	/**
	 * 删除数据
	 * @param id
	 */
	public void delete(int id);
	
	public List<Map<String,String>> queryByCid(int id);
	/**
	 * 删除大纲时把该大纲下的讲义修改为未分配状态
	 * @param catalog
	 */
	public void updateCatalog(int catalog);
	
	
	

}
