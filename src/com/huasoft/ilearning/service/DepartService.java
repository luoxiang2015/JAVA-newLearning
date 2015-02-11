package com.huasoft.ilearning.service;

import java.util.List;

import com.huasoft.ilearning.bean.Depart;

public interface DepartService {
	
	public void insertOrUpdate(Depart depart);
	
	public List<Depart> query();
	
	public Depart getById(int id);
	
	public void delete(int id);

}
