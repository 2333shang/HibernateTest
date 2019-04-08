package com.hx.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.hx.domain.Goods;


@XmlRootElement(name="shoppingCart")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShoppingCartJAXB {

	@XmlElement(name="shoopingType")
	private List<Goods> list=new ArrayList<Goods>();

	public ShoppingCartJAXB() {
		super();
	}

	public ShoppingCartJAXB(List<Goods> list) {
		super();
		this.list = list;
	}

	public List<Goods> getList() {
		return list;
	}

	public void setList(List<Goods> list) {
		this.list = list;
	}
	
}
