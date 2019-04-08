package com.hx.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
	
	private List<Goods> goods=new ArrayList<Goods>();
	
	public List<Goods> getGoods(){
		return goods;
	}
	
	public void addToCart(Goods s){
		boolean flag=false;
		int a=0;
		for(int i=0;i<goods.size();i++) {
			if(goods.get(i).getGoodsName().equals(s.getGoodsName())) {
				flag=true;
				a=i;
			}
		}
		if(flag) {
			Goods g=goods.get(a);
			g.setNumber(g.getNumber()+1);
		}else {
			s.setNumber(1);
			goods.add(s);
		}
	}
	
	public int getTotalNumber() {
		int total=0;
		
		for(Goods m:goods) {
			total+=m.getNumber();
		}
		return total;
	}
	
	public double getTotalPrice() {
		double money=0;
		for(Goods m:goods) {
			money+=m.getNumber()*m.getPrice();
		}
		return money;
	}

	@Override
	public String toString() {
		return "ShoppingCart [goods=" + goods + "]";
	}
	
}
