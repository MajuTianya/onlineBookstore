package cn.maju.goods.page;

public class Expression {
	private String name;
	private String operater;
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOperater() {
		return operater;
	}
	public void setOperater(String operater) {
		this.operater = operater;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Expression [name=" + name + ", operater=" + operater
				+ ", value=" + value + "]";
	}
	
	public Expression() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Expression(String name, String operater, String value) {
		super();
		this.name = name;
		this.operater = operater;
		this.value = value;
	}
	

}
