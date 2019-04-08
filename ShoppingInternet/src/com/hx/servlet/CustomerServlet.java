package com.hx.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hx.dao.CustomerDAO;
import com.hx.dao.CustomerDAOImpl;
import com.hx.domain.Customer;
import com.hx.domain.Goods;
import com.hx.domain.GoodsSelect;
import com.hx.domain.ShoppingCart;
import com.hx.jaxb.ShoppingCartJAXB;
import com.hx.jaxb.ShoppingJAXB;

public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDAO customerDAO=new CustomerDAOImpl();
	private static Map<String,ShoppingCart> cs=new HashMap<String,ShoppingCart>();
	ShoppingCart sc=null;
	private String contentPath;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contentPath=request.getContextPath();
		String methodName=request.getServletPath();
		methodName=methodName.substring(1);
		methodName=methodName.substring(0,methodName.length()-3);
		try {
			Method method=getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String sql="select username,password from customer where username=?";
		Customer customer=customerDAO.getCustomerWithName(sql,username);
		request.setAttribute("userMessage2", username);
		if(customer==null) {
			request.setAttribute("userMessage1", "用户名错误！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else {
			if(customer.getPassword().equals(password)) {
				sc=cs.get(username);
				if(sc==null) {
					sc=new ShoppingCart();
					cs.put(username, sc);
					request.getSession().setAttribute("shoppingCart", sc);
				}else {
					request.getSession().setAttribute("shoppingCart", sc);
				}
				request.getSession().setAttribute("username", username);
				response.sendRedirect(contentPath+"/shopping.jsp");
			}else {
				request.setAttribute("pasdMessage", "密码错误！");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
	}
	
	private void ajaxRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result=null;
		String username=request.getParameter("username");
		String sql="select username,password from customer where username=?";
		Customer customer=customerDAO.getCustomerWithName(sql,username);
		if(customer==null) {
			result="<font color='green'>该用户名可以使用</font>";
		}else {
			result="<font color='red'>该用户名已被注册</font>";
		}
		response.setContentType("text/html");
		response.getWriter().print(result);
	}
	
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String sql="insert into customer value(?,?)";
		customerDAO.addCustomer(sql,username,password);
		request.getRequestDispatcher("/shopping.jsp").forward(request, response);
	}
	
	private void selectGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql="select goodsName,price from goods";
		List<Goods> list=customerDAO.selectGoods(sql);
		request.setAttribute("goodsLength", list.size());
		String page=request.getParameter("page");
		if(page==null) {
			page="1";
		}
		int p=Integer.parseInt(page);
		sql="select goodsName,price from goods  LIMIT ?,?";
		list=customerDAO.selectGoods(sql,(p-1)*5,5);
		request.setAttribute("goods", list);
		request.setAttribute("page", p);
		request.setAttribute("type", 2);
		request.getRequestDispatcher("/shopping.jsp").forward(request, response);
	}
	private void selectGoodsByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selectName=request.getParameter("selectName");
		selectName=selectName.trim();
		String page=request.getParameter("page");
		if(page==null) {
			page="1";
		}
		int p=Integer.parseInt(page);
		GoodsSelect goodsSelect=new GoodsSelect();
		goodsSelect.setGoodsName(selectName);
		String sql="select goodsName,price from goods where goodsName=?";
		List<Goods> list=customerDAO.selectGoods(sql,goodsSelect.getGoodsName());
		request.setAttribute("goodsLength", list.size());
		list=customerDAO.selectGoodsByName(goodsSelect,p);
		request.setAttribute("goods", list);
		request.setAttribute("page", p);
		request.setAttribute("type", 1);
		request.getRequestDispatcher("/shopping.jsp").forward(request, response);
	}
	
	private void addToShoppingCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String goodsName=request.getParameter("goodsName");
		String sql="select goodsName,price from goods where goodsName=?";
		Goods g=customerDAO.getGoodsWithName(sql,goodsName);
		//ShoppingJAXB.marshalXML(g);
		sc.addToCart(g);
		request.getSession().setAttribute("shoppingCart", sc);
		String result="{\"goodsName\":"+"\""+goodsName+"\""+"}";
		response.setContentType("text/javascript");
		response.getWriter().print(result);
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("username");
		response.sendRedirect(contentPath+"/shopping.jsp");
	}
	
	private void pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String goodsName=request.getParameter("goodsName");
		ShoppingCart sc=(ShoppingCart)request.getSession().getAttribute("shoppingCart");
		List<Goods> g=sc.getGoods();
		int a=0;
		for(int i=0;i<g.size();i++) {
			if(g.get(i).getGoodsName().equals(goodsName)) {
				a=i;
			}
		}
		g.remove(a);
		request.getSession().setAttribute("shoppingCart", sc);
		response.sendRedirect(contentPath+"/pay.jsp");
	}
	private void payAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String good=request.getParameter("goodsNameArr");
		String[] gg=good.split(",");
		List<Goods> go=sc.getGoods();
		int a=0;
		for(int i=0;i<go.size();i++) {
			if(a<gg.length) {
				if(go.get(i).getGoodsName().equals(gg[a])) {
					go.remove(i);
					a++;
					i=-1;
				}
			}else break;
		}
		request.getSession().setAttribute("shoppingCart", sc);
		response.sendRedirect(contentPath+"/pay.jsp");
	}
	private void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String goodsName=request.getParameter("goodsName");
		List<Goods> g=sc.getGoods();
		for(int i=0;i<g.size();i++) {
			if(g.get(i).getGoodsName().equals(goodsName)) {
				g.remove(i);
				break;
			}
		}
		request.getSession().setAttribute("shoppingCart", sc);
		String result="{\"goodsNameMes\":"+"\""+goodsName+"已成功删除\""+"}";
		response.setContentType("text/javascript");
		response.getWriter().print(result);
	}
	private void delAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("shoppingCart");
		response.sendRedirect(contentPath+"/shoppingCart.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
