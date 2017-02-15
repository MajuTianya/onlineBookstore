package cn.maju.goods.author.domain;

public class Author {
	private String aid;
	private String aname;
	private String work1;
	private String work2;
	private String work3;
	private String describle;
	private String image_b;
	private String image_s;
	private int index;
	public String getAid() {
		return aid;
	}
	public void setAid(String Aid) {
		this.aid = Aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getWork1() {
		return work1;
	}
	public void setWork1(String work1) {
		this.work1 = work1;
	}
	public String getWork2() {
		return work2;
	}
	public void setWork2(String work2) {
		this.work2 = work2;
	}
	public String getWork3() {
		return work3;
	}
	public void setWork3(String work3) {
		this.work3 = work3;
	}
	public String getDescrible() {
		return describle;
	}
	public void setDescrible(String describle) {
		this.describle = describle;
	}
	public String getImage_b() {
		return image_b;
	}
	public void setImage_b(String image_b) {
		this.image_b = image_b;
	}
	public String getImage_s() {
		return image_s;
	}
	public void setImage_s(String image_s) {
		this.image_s = image_s;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	@Override
	public String toString() {
		return "Author [bid=" + aid + ", bname=" + aname + ", work1=" + work1
				+ ", work2=" + work2 + ", work3=" + work3 + ", describle="
				+ describle + ", image_b=" + image_b + ", image_s=" + image_s
				+ ", index=" + index + "]";
	}
	
	

}
