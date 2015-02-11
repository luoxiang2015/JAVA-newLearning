package com.huasoft.ilearning.bean;

import com.google.gson.annotations.Expose;

public class Depart {
	
	@Expose
	private int id;
	@Expose
	private String number;
	@Expose
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
