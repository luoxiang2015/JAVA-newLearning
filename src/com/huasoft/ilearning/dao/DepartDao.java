package com.huasoft.ilearning.dao;

import java.util.List;

import com.huasoft.ilearning.bean.Depart;

public interface DepartDao {
	/**
	 * 保存或修改数据
	 * @param depart
	 */
	public void saveOrUpdate(Depart depart);
	/**
	 * 查询全部
	 * @return
	 */
	public List<Depart> queryAll();
	/**
	 * 通过id查找数据
	 * @param id
	 * @return
	 */
	public Depart getDepartById(int id);
	
	/**
	 * 删除数据
	 * @param id
	 */
	public void delete(int id);
	

}
