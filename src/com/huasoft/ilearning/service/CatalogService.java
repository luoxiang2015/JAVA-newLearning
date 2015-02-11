package com.huasoft.ilearning.service;

import java.util.List;

import com.huasoft.ilearning.bean.Catalog;

public interface CatalogService {
	
	public void insert(Catalog catalog);
	
	public void update (Catalog catalog);
	
	public List<Catalog> queryChildren(int id);
	
	public Catalog getById(int id);
	
	public void delete(int id);
	
	public String getCode(int id,String code);

}
