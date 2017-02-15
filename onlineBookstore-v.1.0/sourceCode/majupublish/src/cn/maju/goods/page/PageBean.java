package cn.maju.goods.page;

import java.util.List;

public class PageBean<T> {
	private int pc;//当前页码-->
	private int tr;//总记录数-->从数据库获取
	private int ps;//每页记录数-->从系统获取
	private List<T> dataList;//当前页记录-->从数据库获取
	private String url;//请求的url
	
	/**
	 * 计算总页数
	 * @return
	 */
	public int getTp(){
		int tp=tr/ps;
		return tr%ps==0?tp:tp+1;
	}

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
	
	
}
