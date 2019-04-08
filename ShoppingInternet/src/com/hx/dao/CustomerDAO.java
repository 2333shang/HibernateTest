package com.hx.dao;

import java.util.List;

import com.hx.domain.Customer;
import com.hx.domain.Goods;
import com.hx.domain.GoodsSelect;

public interface CustomerDAO {

	public void addCustomer(String sql,Object...args);
	
	public Customer getCustomerWithName(String sql,String username);
	
	public List<Goods> selectGoods(String sql,Object...args);
	
	public List<Goods> selectGoodsByName(GoodsSelect gs,int page);
	
	public Goods getGoodsWithName(String sql,String goodsName);
}
