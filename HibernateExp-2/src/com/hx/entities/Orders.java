package com.hx.entities;

public class Orders {

	private Integer id;
	private String orderName;
	
	private User user;
	
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Orders(Integer id, String orderName) {
		super();
		this.id = id;
		this.orderName = orderName;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
