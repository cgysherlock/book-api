package com.me.kit;

import java.io.File;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.me.model.Dictionary;
import com.me.plugin.SpringContextHolder;

public class XmlKit {

	private static Map<String, Dictionary> dicMap = new HashMap<String, Dictionary>();

	/**
	 * 读取xml中的信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static void xmlReader() {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(new File(SpringContextHolder.getResourceRootRealPath() + "/dicts.xml"));
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Element root = document.getRootElement();
		List<Element> list = root.elements();
		for (Element e : list) {
			String type = e.attributeValue("type");
			String name = e.attributeValue("name");
			Dictionary dic = new Dictionary();
			dic.setType(type);
			dic.setName(name);
			for (Element node : (List<Element>) e.elements()) {
				dic.put(Integer.parseInt(node.attributeValue("value")), node.attributeValue("label"));
			}
			dicMap.put(type, dic);
		}

	}

	/**
	 * 根据类型获得字典
	 * 
	 * @param type
	 * @return
	 */
	public static Dictionary get(String type) {
		if (dicMap.size() < 1) {
			xmlReader();
		}
		return dicMap.get(type);
	}

	/**
	 * 清楚字典
	 */
	public static void clearXmlMap() {
		dicMap.clear();
	}
}
