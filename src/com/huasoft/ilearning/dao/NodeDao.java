package com.huasoft.ilearning.dao;

import java.util.List;

import com.huasoft.ilearning.bean.Node;

public interface NodeDao {
	/**
	 * ����
	 * @param depart
	 */
	public void save(Node node);
	
	public void update(Node node);
	
	/**
	 * ����id��ö���
	 * @param id
	 * @return
	 */
	public Node getNodeById(int id);
	
	/**
	 * ɾ��
	 * @param id
	 */
	public void delete(int id);
	
	public List<Node> queryAllByPage(String hql,int curpage,int pageSize,List<Object> params);
	public int getTotal(String hql,List<Object> params);
	
	
	
	

}
