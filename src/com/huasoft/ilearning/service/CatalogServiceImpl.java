package com.huasoft.ilearning.service;

import java.util.List;

import com.huasoft.ilearning.bean.Catalog;
import com.huasoft.ilearning.dao.CatalogDao;
import com.huasoft.ilearning.dao.LectureDao;

public class CatalogServiceImpl implements CatalogService {

	private CatalogDao dao;
	private LectureDao ldao;
	
	
	public void setDao(CatalogDao dao) {
		this.dao = dao;
	}

	public void setLdao(LectureDao ldao) {
		this.ldao = ldao;
	}

	@Override
	public void insert(Catalog catalog) {
		
		dao.save(catalog);
		

	}

	@Override
	public List<Catalog> queryChildren(int id) {
		return dao.queryByPid(id);
	}

	@Override
	public Catalog getById(int id) {
		return dao.getCatalogById(id);
	}

	@Override
	public void delete(int id) {
		
		dao.delete(id);
		ldao.updateCatalog(id);
		
	}

	/**
	 * 生产子节点编号
	 * id为父节点id
	 * code为父节点编号
	 */
	@Override
	public String getCode(int id,String code) {
		String temp=dao.getMaxCode(id);//获取当前节点下最大编号
		if(temp==null)//如果没有，表示是第一个编号
			return code+"001";
		String c=temp.substring(temp.length()-4);//取得后四位进行算术加一
		String thecode=code.substring(0,code.length()-1)+(Integer.parseInt(c)+1);
		return thecode;
	}

	@Override
	public void update(Catalog catalog) {
		dao.update(catalog);
	}

}
