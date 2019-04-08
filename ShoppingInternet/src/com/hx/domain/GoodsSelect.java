package com.hx.domain;

public class GoodsSelect {

	private String goodsName;	
	private String price;
	public GoodsSelect() {
		super();
	}
	public GoodsSelect(String goodsName, String price) {
		super();
		this.goodsName = goodsName;
		this.price = price;
	}
	public String getGoodsName() {
		if(goodsName==null) {
			return "%%";
		}else{
			return "%"+goodsName+"%";
		}
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getPrice() {
		if(price==null) {
			return "%%";
		}else{
			return "%"+price+"%";
		}
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
}
