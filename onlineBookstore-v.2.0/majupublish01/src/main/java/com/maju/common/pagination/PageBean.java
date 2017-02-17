package com.maju.common.pagination;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class PageBean<T> {
	private int pc;//当前页码-->
	private int tr;//总记录数-->从数据库获取
	private int ps;//每页记录数-->从系统获取
	private List<T> dataList;//当前页记录-->从数据库获取
	private String url;//请求的url
	
	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public int getTr() {
		return tr;
	}

	public void setTr(int tr) {
		this.tr = tr;
	}

	public int getPs() {
		return ps;
	}

	public void setPs(int ps) {
		this.ps = ps;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "PageBean [pc=" + pc + ", tr=" + tr + ", ps=" + ps
				+ ", dataList=" + dataList + ", url=" + url + "]";
	}
	
	/**
	 * 获取当前pc
	 * 传过来pc值为空或小于0时，设为1
	 * 否则返回传过来的值
	 */
	public static int pc(Integer pageCode) {
		// TODO Auto-generated method stub
		if(pageCode == null || pageCode <1) {
			return 1;
		} else{
			return pageCode;
		}
	}
	/**
	 * 计算起始页
	 * 这个方法是静态方法，它只是方法，没有操作类中的数据，所以可以这样写
	 * @param pageCode
	 * @param pageSize
	 * @return
	 */
	public static int startRow(int pageCode, int pageSize) {
		// TODO Auto-generated method stub
		return (pageCode-1)*pageSize;
	}
	/**
	 * 计算总页数
	 * @return
	 */
	public int getTp(){
		int tp=tr/ps;
		return tr%ps==0?tp:tp+1;
	}
	
	/**
	 * 获取请求的url，但去除pc参数
	 */
	public String url(String url, String params) {
		if(StringUtils.isNotBlank(params)){
			return  url + "?" + params;
		}else{
			return  url + "?";
		}
	}
	
}
