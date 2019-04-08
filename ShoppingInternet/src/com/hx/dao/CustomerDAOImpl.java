package com.hx.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.hx.domain.Customer;
import com.hx.domain.Goods;
import com.hx.domain.GoodsSelect;
import com.hx.db.DBConnection;

public class CustomerDAOImpl implements CustomerDAO{
	
	private QueryRunner queryrunner=new QueryRunner();
	@Override
	public void addCustomer(String sql, Object... args) {
		Connection conn=null;
		try {
			conn=DBConnection.getConnection();
			queryrunner.update(conn, sql,args);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.releaseConnection(conn);
		}
	}


	@Override
	public Customer getCustomerWithName(String sql,String username) {
		Connection conn=null;
		try {
			conn=DBConnection.getConnection();
			return queryrunner.query(conn,sql,new BeanHandler<Customer>(Customer.class),username);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.releaseConnection(conn);
		}
		return null;
	}


	@Override
	public List<Goods> selectGoods(String sql,Object... args) {
		Connection conn=null;
		List<Goods> list=new ArrayList<>();
		try {
			conn=DBConnection.getConnection();
			list=queryrunner.query(conn,sql,new BeanListHandler<Goods>(Goods.class),args);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.releaseConnection(conn);
		}
		return list;
	}


	@Override
	public List<Goods> selectGoodsByName(GoodsSelect gs,int page) {
		String sql="select goodsName,price from goods where goodsName like ? LIMIT ?,?";
		return selectGoods(sql,gs.getGoodsName(),(page-1)*5,5);

	}


	@Override
	public Goods getGoodsWithName(String sql, String goodsName) {
		Connection conn=null;
		try {
			conn=DBConnection.getConnection();
			return queryrunner.query(conn,sql,new BeanHandler<Goods>(Goods.class),goodsName);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.releaseConnection(conn);
		}
		return null;
	}

}
