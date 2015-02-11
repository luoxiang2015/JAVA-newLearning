package com.huasoft.ilearning.bean;

import com.google.gson.annotations.Expose;

public class Lecture {
	
	@Expose
	private int id;
	@Expose
	private String title; //����
	private String content;//����
	@Expose
	private String creater;//������
	@Expose
	private String createDate;//��������
	
	private int  catalog;//�������
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getCatalog() {
		return catalog;
	}
	public void setCatalog(int catalog) {
		this.catalog = catalog;
	}
	
	
	

}
