package com.hx.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Goods {
	
	private String goodsName;	
	private double price;
	private int number;
	
	public Goods() {
		super();
	}


	public Goods(String goodsName, double price, int number) {
		super();
		this.goodsName = goodsName;
		this.price = price;
		this.number = number;
	}


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "Goods [goodsName=" + goodsName + ", price=" + price + ", number=" + number + "]";
	}
	
}
