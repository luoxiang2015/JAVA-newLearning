package com.huasoft.ilearning.service;

import java.util.Map;

import com.huasoft.ilearning.bean.Node;
import com.huasoft.ilearning.util.Page;

public interface NodeService {
	
	public void insert(Node node);
	
	public void update (Node node);
	
	public Page<Node> queryAllByPage(int curpage,int pageSize,Map<String,Object> params);
	
	public Node getById(int id);
	
	public void delete(int id);
	/**
	 * 删除后提取下一条补充
	 * @param id
	 * @param page
	 * @param params
	 * @return
	 */
	public Node delete(int id,int page,Map<String,Object> params);
	

}
