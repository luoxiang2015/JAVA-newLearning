package com.huasoft.ilearning.dao;

import java.util.List;

import com.huasoft.ilearning.bean.Catalog;

public interface CatalogDao {
	/**
	 * ��������
	 * @param depart
	 */
	public void save(Catalog catalog);
	
	public void update(Catalog catalog);
	/**
	 * ��ѯȫ��
	 * @return
	 */
	public List<Catalog> queryByPid(int id);
	/**
	 * ͨ��id��������
	 * @param id
	 * @return
	 */
	public Catalog getCatalogById(int id);
	
	/**
	 * ɾ������
	 * @param id
	 */
	public void delete(int id);
	/**
	 * ���ݸ��ڵ�id��ѯ�ӽڵ���code���ֵ
	 * @param id
	 * @return
	 */
	public String getMaxCode(int id);
	
	
	

}
