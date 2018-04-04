/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.services.config.module.sysconf;

import cn.com.frame.common.builder.Sys;
import cn.com.frame.common.tools.xml.XMLInstance;
import cn.com.frame.common.util.ParamUtil;
import cn.com.frame.services.config.module.common.ConfigServicesRunning;
import cn.com.frame.services.config.service.ConfigServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;


/**
 * 系统配置线程
 * 
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2012-9-18
 */
public class SystemConfig extends ConfigServicesRunning {

	private ServletContext application;

	// 格式见包说明
	/**
	 * 构造函数默认
	 */
	public SystemConfig(ServletContext application) {
		super(ParamUtil.CLASSES_PATH + ConfigServices.XML_SYS_CONFIG,
				ConfigServices.CONFIG_SYS_CONFIG);
		// TODO Auto-generated constructor stub
		this.application = application;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.system.services.config.module.common.ConfigModel#readConfig()
	 */
	@Override
	public Map readConfig() {
		// TODO Auto-generated method stub
		String path = configPath;
		try {
			XMLInstance instance = Sys.getXmlTools().readConfig(configPath);
			List<XMLInstance> childNode = instance.getChildNodes();
			Map configMap = new HashMap();
			String sysname = "";
			String sysid = "";
			String syscom = "";
			String admin_u = "";
			String admin_p = "";
			List resultList = new ArrayList();
			for (Iterator<XMLInstance> it = childNode.iterator(); it.hasNext();) {
				XMLInstance node = it.next();
				Map tempMap = new HashMap();
				String temp = (String) node.getAttributeMap().get("systemname");
				if (temp != null && !temp.equals("")) {
					tempMap.put("systemname", temp);
					sysname = temp;
					// 配置文件放入全局变量
					ParamUtil.SYSTEM_NAME = sysname;
				}
				temp = (String) node.getAttributeMap().get("systemid");
				if (temp != null && !temp.equals("")) {
					tempMap.put("systemid", temp);
					sysid = temp;
					// 配置文件放入全局变量
					ParamUtil.SYSTEM_ID = sysid;
				}
				temp = (String) node.getAttributeMap().get("systemorgcom");
				if (temp != null && !temp.equals("")) {
					tempMap.put("systemorgcom", temp);
					syscom = temp;
					// 配置文件放入全局变量
					ParamUtil.SYSTEM_ORG_COM = syscom;
				}
				temp = (String) node.getAttributeMap().get("sysadmin_u");
				if (temp != null && !temp.equals("")) {
					tempMap.put("sysadmin_u", temp);
					admin_u = temp;
					// 配置文件放入全局变量
					ParamUtil.SYS_USER_NAME = admin_u;
				}
				temp = (String) node.getAttributeMap().get("sysadmin_p");
				if (temp != null && !temp.equals("")) {
					tempMap.put("sysadmin_p", temp);
					admin_p = temp;
					// 配置文件放入全局变量
					ParamUtil.SYS_USER_PASS = admin_p;
				}
				temp = (String) node.getAttributeMap().get("databasetype");
				if (temp != null && !temp.equals("")) {
					tempMap.put("databasetype", temp);
					// 配置文件放入全局变量
					ParamUtil.DB_TYPE = temp;
				}
				resultList.add(tempMap);
			}
			configMap.put("resultList", resultList);
			ConfigServices.intoConfig(configKey, configMap);
			System.out.println("系统配置文件读取成功：系统名称“" + sysname + "”，系统编码“" + sysid + "”，版权＂" + syscom + "＂");

			application.setAttribute("SYS_CH_NAME", ParamUtil.SYSTEM_NAME);
			application.setAttribute("SYS_ID_NAME", ParamUtil.SYSTEM_ID);
			application.setAttribute("SYS_ORG_COM", ParamUtil.SYSTEM_ORG_COM);

			return configMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new HashMap();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.system.services.config.module.common.ConfigModel#writeConfig(java
	 * .util.Map)
	 */
	@Override
	public boolean writeConfig(Map config) {
		// TODO Auto-generated method stub
		if (config == null) {
			config = (Map) ConfigServices
					.findConfigByKey(ConfigServices.CONFIG_SYS_CONFIG);
		}
		XMLInstance root = new XMLInstance();
		root.setNodeName("ROOT");
		List<XMLInstance> childNode = new ArrayList<XMLInstance>();
		XMLInstance headChild = new XMLInstance();
		headChild.setNodeName("CONFIG");
		for (Iterator it = config.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			XMLInstance xml = new XMLInstance();
			// 设置系统名称
			xml.setNodeName("ATTR");
			Map<String, Object> attributeMap = new HashMap<String, Object>();
			attributeMap.put("systemname", key);
			attributeMap.put("systemid", config.get(key));
			xml.setAttributeMap(attributeMap);
			childNode.add(xml);
		}
		headChild.setChildNodes(childNode);
		childNode.add(headChild);
		childNode = new ArrayList<XMLInstance>();
		root.setChildNodes(childNode);
		try {
			Sys.getXmlTools().writeConfig(root, configPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
