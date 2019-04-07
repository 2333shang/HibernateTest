package com.hx.entities;

public class Orders2 {

	private Integer id;
	private String orderName;
	
	public Orders2() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Orders2(Integer id, String orderName) {
		super();
		this.id = id;
		this.orderName = orderName;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderName=" + orderName + "]";
	}
	
}
