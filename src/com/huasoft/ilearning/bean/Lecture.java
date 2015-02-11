package com.huasoft.ilearning.bean;

import com.google.gson.annotations.Expose;

public class Lecture {
	
	@Expose
	private int id;
	@Expose
	private String title; //主题
	private String content;//内容
	@Expose
	private String creater;//创建者
	@Expose
	private String createDate;//创建日期
	
	private int  catalog;//所属大纲
	
	
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
