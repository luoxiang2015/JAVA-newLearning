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
	 * �����ӽڵ���
	 * idΪ���ڵ�id
	 * codeΪ���ڵ���
	 */
	@Override
	public String getCode(int id,String code) {
		String temp=dao.getMaxCode(id);//��ȡ��ǰ�ڵ��������
		if(temp==null)//���û�У���ʾ�ǵ�һ�����
			return code+"001";
		String c=temp.substring(temp.length()-4);//ȡ�ú���λ����������һ
		String thecode=code.substring(0,code.length()-1)+(Integer.parseInt(c)+1);
		return thecode;
	}

	@Override
	public void update(Catalog catalog) {
		dao.update(catalog);
	}

}
