package com.bah.na.asc.drools.api.pojo;

public class Data{

	public Object data;
	public String id;

	public int count;

	// getter and setter methods here
	public Data(String id){
		this.id = id;
	}

	public Object getData(){
		return data;
	}

	public void inc(){
		count++;
	}

	public void reset(){
		count = 0;
	}

	public Integer getCount(){
		return this.count;
	}

	public void setCount(Integer count){
		this.count = count;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}
}
