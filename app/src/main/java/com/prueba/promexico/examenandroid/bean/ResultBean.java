package com.prueba.promexico.examenandroid.bean;

public class ResultBean {
	private boolean result;
	private String description;
	
	public ResultBean(){
		result = false;
		description = new String();
	}
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
