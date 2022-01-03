package com.web.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DrinkOrder {
	private Integer OrderID;
	
	private Integer UserID;
	private User user;
	
	private Integer AddrID;
	private UserAddr userAddr;
	
	private float TotalPrice;
	private Date OrderTime;
	private Integer PayState;
	
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	public Integer getOrderID() {
		return OrderID;
	}
	public void setOrderID(Integer orderID) {
		OrderID = orderID;
	}
	public Integer getUserID() {
		return UserID;
	}
	public void setUserID(Integer userID) {
		UserID = userID;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getAddrID() {
		return AddrID;
	}
	public void setAddrID(Integer addrID) {
		AddrID = addrID;
	}
	public UserAddr getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(UserAddr userAddr) {
		this.userAddr = userAddr;
	}
	public float getTotalPrice() {
		return TotalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		TotalPrice = totalPrice;
	}
	public Date getOrderTime() {
		return OrderTime;
	}
	public void setOrderTime(Date orderTime) {
		OrderTime = orderTime;
	}
	public Integer getPayState() {
		return PayState;
	}
	public void setPayState(Integer payState) {
		PayState = payState;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}
