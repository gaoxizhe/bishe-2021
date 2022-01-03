package com.web.entity;

/**
 * 公告实体类
 * @author ASUS
 *
 */
public class Notice {
	
	private Integer n_id;//公告id
	private String title;//公告标题信息
	private String details;//公告信息
	private String n_time;//发布公告的时间
	
	
	public Integer getN_id() {
		return n_id;
	}
	public void setN_id(Integer n_id) {
		this.n_id = n_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getN_time() {
		return n_time;
	}
	public void setN_time(String n_time) {
		this.n_time = n_time;
	}
	
    	

}
