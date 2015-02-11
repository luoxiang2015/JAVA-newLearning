package com.huasoft.ilearning.bean;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class Role {
	
	/**
	 * 
	 */
	@Expose
	private int id;
	@Expose
	private String roleName;
	@Expose
	private Date createDate;
	private String remark;
	private String nodes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getNodes() {
		return nodes;
	}
	public void setNodes(String nodes) {
		this.nodes = nodes;
	}
	
}
