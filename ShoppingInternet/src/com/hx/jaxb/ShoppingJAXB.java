package com.hx.jaxb;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.hx.domain.Goods;

public class ShoppingJAXB {

	public static void marshalXML(Goods g) {
		FileWriter f=null;
		JAXBContext jc=null;
		try {
			f=new FileWriter("shoppingCart.xml",true);
			jc=JAXBContext.newInstance(Goods.class);
			Marshaller m=jc.createMarshaller();
			m.marshal(g, f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void unmarshalXML() {
		File f=null;
		JAXBContext jc=null;
		try {
			f=new File("shoppingCart.xml");
			jc=JAXBContext.newInstance(Goods.class);
			Unmarshaller u=jc.createUnmarshaller();
			ShoppingCartJAXB SC=(ShoppingCartJAXB) u.unmarshal(f);
			List<Goods> list=SC.getList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
