package com.huasoft.ilearning.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huasoft.ilearning.bean.Role;
import com.huasoft.ilearning.dao.RoleDao;
import com.huasoft.ilearning.util.Page;

public class RoleServiceImpl implements RoleService {
	
	private RoleDao dao;

	public void setDao(RoleDao dao) {
		this.dao = dao;
	}

	@Override
	public void delete(int id) {

		dao.delete(id);
	}
	
	public Role delete(int id,int page,Map<String,Object> params){
		dao.delete(id);
		StringBuilder hql=new StringBuilder("from Role where 1=1 ");
		List<Object> values=null;
		if(params!=null){
			values=new ArrayList<Object>();
			if(params.get("roleName")!=null){
				hql.append(" and roleName like ?");
				values.add(params.get("roleName"));
			}
			if(params.get("beginDate")!=null&&params.get("endDate")!=null){
				hql.append(" and createDate between ? and ? ");
				values.add(params.get("beginDate"));
				values.add(params.get("endDate"));
			}
		}
		//�����һҳ��һ�����
		List<Role> list=dao.queryAllByPage(hql.toString(),page, 1,values);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Role getById(int id) {
		return dao.getRoleById(id);
	}

	@Override
	public void insert(Role role) {
		role.setCreateDate(new Date());
		dao.save(role);
	}

	@Override
	public Page<Role> queryAllByPage(int curpage, int pageSize,Map<String,Object> params) {
		Page<Role> page=new Page<Role>();
	
		StringBuilder hql=new StringBuilder("from Role where 1=1 ");
		List<Object> values=null;
		if(params!=null){
			values=new ArrayList<Object>();
			if(params.get("roleName")!=null){
				hql.append(" and roleName like ?");
				values.add("%"+params.get("roleName")+"%");
			}
			if(params.get("beginDate")!=null&&params.get("endDate")!=null){
				hql.append(" and createDate between ? and ? ");
				values.add(params.get("beginDate"));
				values.add(params.get("endDate"));
			}
		}
		hql.append(" order by id desc");
		page.setRows(dao.queryAllByPage(hql.toString(),curpage, pageSize,values));
		page.setTotal(dao.getTotal("select count(*) "+hql.toString(),values));
		return page;
	}

	@Override
	public void update(Role role) {
		Role r=dao.getRoleById(role.getId());
		r.setRemark(role.getRemark());
		r.setRoleName(role.getRoleName());
		//dao.update(employee);
	}
	
	public void updateNodes(int id,String nodes){
		Role r=dao.getRoleById(id);
		r.setNodes(nodes);
	}
	
	public List<Map<String,Object>> getNodes(int id){
		String n=dao.getRoleById(id).getNodes();
		n=","+n+",";
		List<Map<String,Object>> res=dao.getNodes();
		if(n!=null&&(!"".equals(n))){
			for(int i=0;i<res.size();i++){
				Map<String,Object> temp=res.get(i);
				if(n.contains(","+temp.get("id").toString()+",")){
					temp.put("checked", true);
				}
			}
		}
		return res;
	}

}
