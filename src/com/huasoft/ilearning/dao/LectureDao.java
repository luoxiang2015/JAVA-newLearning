package com.huasoft.ilearning.dao;

import java.util.List;
import java.util.Map;

import com.huasoft.ilearning.bean.Lecture;

public interface LectureDao {
	/**
	 * ��������
	 * @param 
	 */
	public void save(Lecture lecture);
	
	public void update(Lecture lecture);
	
	/**
	 * ͨ��id��������
	 * @param id
	 * @return
	 */
	public Lecture getLectureById(int id);
	
	/**
	 * ɾ������
	 * @param id
	 */
	public void delete(int id);
	
	public List<Map<String,String>> queryByCid(int id);
	/**
	 * ɾ�����ʱ�Ѹô���µĽ����޸�Ϊδ����״̬
	 * @param catalog
	 */
	public void updateCatalog(int catalog);
	
	
	

}
