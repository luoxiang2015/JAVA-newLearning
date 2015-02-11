package com.huasoft.ilearning.bean;

import com.google.gson.annotations.SerializedName;

public class Catalog {
	
	private int id;
	private int pid;
	private String code;
	private String name;
	private String remark;
	@SerializedName(value = "isParent")
	private boolean parent ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public boolean getParent() {
		return parent;
	}
	public void setParent(boolean parent) {
		this.parent = parent;
	}
	

}
