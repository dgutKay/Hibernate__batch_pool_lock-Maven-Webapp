package com.kay.hibernate.entity;

public class Order {
	private int id;
	private String orderNo;
	private int orderMoney;
	private User user;
	// 这个订单属于哪个用户

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(int orderMoney) {
		this.orderMoney = orderMoney;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
