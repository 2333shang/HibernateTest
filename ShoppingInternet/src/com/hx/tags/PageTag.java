package com.hx.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PageTag extends SimpleTagSupport{
	
	private String link;
	
	public void setLink(String link) {
		this.link = link;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		StringWriter sw=new StringWriter();
		getJspBody().invoke(sw);
		String url="<a href="+link+">"+sw.toString()+"</a>";
		getJspContext().getOut().print(url);
	}
	
}
