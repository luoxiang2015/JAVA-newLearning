package com.huasoft.ilearning.util;

import java.util.List;

import com.google.gson.annotations.Expose;

public class Page<T> {
	@Expose
	private int total;
	@Expose
	private List<T> rows;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	

}
