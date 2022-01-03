package com.web.entity;

public class UserAddr {
	private Integer UserID;
	private Integer AddrID;
	private String City;
	private String County;
	private String Street;
	private String HouseNum;
	
	public Integer getUserID() {
		return UserID;
	}
	public void setUserID(Integer userID) {
		UserID = userID;
	}
	public Integer getAddrID() {
		return AddrID;
	}
	public void setAddrID(Integer addrID) {
		AddrID = addrID;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getCounty() {
		return County;
	}
	public void setCounty(String county) {
		County = county;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public String getHouseNum() {
		return HouseNum;
	}
	public void setHouseNum(String houseNum) {
		HouseNum = houseNum;
	}
}
