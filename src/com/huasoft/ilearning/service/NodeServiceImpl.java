package com.huasoft.ilearning.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huasoft.ilearning.bean.Node;
import com.huasoft.ilearning.dao.NodeDao;
import com.huasoft.ilearning.util.Page;

public class NodeServiceImpl implements NodeService {
	
	private NodeDao dao;

	public void setDao(NodeDao dao) {
		this.dao = dao;
	}

	@Override
	public void delete(int id) {

		dao.delete(id);
	}
	
	public Node delete(int id,int page,Map<String,Object> params){
		dao.delete(id);
		StringBuilder hql=new StringBuilder("from Node where 1=1 ");
		List<Object> values=null;
		if(params!=null){
			values=new ArrayList<Object>();
			if(params.get("nodeName")!=null){
				hql.append(" and name like ?");
				values.add("%"+params.get("nodeName")+"%");
			}
			if(params.get("type")!=null){
				hql.append(" and type = ? ");
				values.add(params.get("type"));
			}
		}
		//查出下一页第一个数据
		List<Node> list=dao.queryAllByPage(hql.toString(),page, 1,values);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Node getById(int id) {
		return dao.getNodeById(id);
	}

	@Override
	public void insert(Node node) {
		dao.save(node);
	}

	@Override
	public Page<Node> queryAllByPage(int curpage, int pageSize,Map<String,Object> params) {
		Page<Node> page=new Page<Node>();
		StringBuilder hql=new StringBuilder("from Node where 1=1 ");
		List<Object> values=null;
		if(params!=null){
			values=new ArrayList<Object>();
			if(params.get("nodeName")!=null){
				hql.append(" and name like ?");
				values.add("%"+params.get("nodeName")+"%");
			}
			if(params.get("type")!=null){
				hql.append(" and type = ? ");
				values.add(params.get("type"));
			}
		}
		hql.append(" order by id desc");
		page.setRows(dao.queryAllByPage(hql.toString(),curpage, pageSize,values));
		page.setTotal(dao.getTotal("select count(*) "+hql.toString(),values));
		return page;
	}

	@Override
	public void update(Node node) {
		dao.update(node);
	}

}
