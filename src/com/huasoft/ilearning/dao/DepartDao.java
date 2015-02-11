package com.huasoft.ilearning.dao;

import java.util.List;

import com.huasoft.ilearning.bean.Depart;

public interface DepartDao {
	/**
	 * ������޸�����
	 * @param depart
	 */
	public void saveOrUpdate(Depart depart);
	/**
	 * ��ѯȫ��
	 * @return
	 */
	public List<Depart> queryAll();
	/**
	 * ͨ��id��������
	 * @param id
	 * @return
	 */
	public Depart getDepartById(int id);
	
	/**
	 * ɾ������
	 * @param id
	 */
	public void delete(int id);
	

}
