package com.huasoft.ilearning.dao;

import java.util.List;

import com.huasoft.ilearning.bean.Node;

public interface NodeDao {
	/**
	 * 保存
	 * @param depart
	 */
	public void save(Node node);
	
	public void update(Node node);
	
	/**
	 * 根据id获得对象
	 * @param id
	 * @return
	 */
	public Node getNodeById(int id);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(int id);
	
	public List<Node> queryAllByPage(String hql,int curpage,int pageSize,List<Object> params);
	public int getTotal(String hql,List<Object> params);
	
	
	
	

}
