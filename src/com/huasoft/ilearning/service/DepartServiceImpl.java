package com.huasoft.ilearning.service;

import java.util.List;

import com.huasoft.ilearning.bean.Depart;
import com.huasoft.ilearning.dao.DepartDao;
import com.huasoft.ilearning.dao.DepartDaoImpl;

public class DepartServiceImpl implements DepartService {

	private DepartDao dao;
	
	public void setDao(DepartDao dao) {
		this.dao = dao;
	}

	@Override
	public void insertOrUpdate(Depart depart) {
		
		dao.saveOrUpdate(depart);
		

	}

	@Override
	public List<Depart> query() {
		return dao.queryAll();
	}

	@Override
	public Depart getById(int id) {
		return dao.getDepartById(id);
	}

	@Override
	public void delete(int id) {
		
		dao.delete(id);
		
	}

}
