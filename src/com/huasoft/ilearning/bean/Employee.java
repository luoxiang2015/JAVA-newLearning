package com.huasoft.ilearning.bean;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.annotations.Expose;

public class Employee implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 32432432423L;
	@Expose
	private int id;
	@Expose
	private String username;
	private String pwd;
	@Expose
	private String truename;
	@Expose
	private int sex;
	@Expose
	private Date birthDay;
	@Expose
	private Date createDate;
	private String remark;
	private String roles;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
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
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	

}
