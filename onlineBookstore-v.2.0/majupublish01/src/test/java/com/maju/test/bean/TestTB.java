package com.maju.test.bean;
import java.util.Date;

/**
 * 测试JavaBean
 * @author chenli
 *
 */
public class TestTB {
	
	private Integer id;
	private String name;
	private Date birthday;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
