package com.maju.core.admin.product.bean;

import com.maju.common.web.ConstantsCommon;
import com.maju.core.admin.category.bean.Category;

public class Product {
	private String bid;//id
	private String bname;//图书
	private String author;//作者
	private Double price;//定价
	private Double currPrice;//当前价
	private Double discount;//折扣
	private String press;//出版社
	private String publishtime;//出版日期
	private Integer edition;//版次
	private Integer pageNum;//页数
	private Integer wordNum;//字数
	private String printtime;//印刷时间  
	private Integer booksize;//开本
	private String paper;//纸质
	private Category category;//所属分类
	private String cid;//所属分类id
	private String image_w;//大图
	private String image_b;//小图
	private String bookList;//书本清单
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getCurrPrice() {
		return currPrice;
	}
	public void setCurrPrice(Double currPrice) {
		this.currPrice = currPrice;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}
	public String getPrinttime() {
		return printtime;
	}
	public void setPrinttime(String printtime) {
		this.printtime = printtime;
	}
	public String getPaper() {
		return paper;
	}
	public void setPaper(String paper) {
		this.paper = paper;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getImage_w() {
		return image_w;
	}
	public void setImage_w(String image_w) {
		this.image_w = image_w;
	}
	public String getImage_b() {
		return image_b;
	}
	public void setImage_b(String image_b) {
		this.image_b = image_b;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public Integer getEdition() {
		return edition;
	}
	public void setEdition(Integer edition) {
		this.edition = edition;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getWordNum() {
		return wordNum;
	}
	public void setWordNum(Integer wordNum) {
		this.wordNum = wordNum;
	}
	public Integer getBooksize() {
		return booksize;
	}
	public void setBooksize(Integer booksize) {
		this.booksize = booksize;
	}
	public String getBookList() {
		return bookList;
	}
	public void setBookList(String bookList) {
		this.bookList = bookList;
	}

}
