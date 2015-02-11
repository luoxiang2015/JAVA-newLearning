package com.huasoft.ilearning.dao;

import java.util.List;

import com.huasoft.ilearning.bean.Catalog;

public interface CatalogDao {
	/**
	 * 保存数据
	 * @param depart
	 */
	public void save(Catalog catalog);
	
	public void update(Catalog catalog);
	/**
	 * 查询全部
	 * @return
	 */
	public List<Catalog> queryByPid(int id);
	/**
	 * 通过id查找数据
	 * @param id
	 * @return
	 */
	public Catalog getCatalogById(int id);
	
	/**
	 * 删除数据
	 * @param id
	 */
	public void delete(int id);
	/**
	 * 根据父节点id查询子节点中code最大值
	 * @param id
	 * @return
	 */
	public String getMaxCode(int id);
	
	
	

}
