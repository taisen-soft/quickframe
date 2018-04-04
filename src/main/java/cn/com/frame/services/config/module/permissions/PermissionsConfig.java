/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.services.config.module.permissions;

import cn.com.frame.common.builder.Sys;
import cn.com.frame.common.tools.xml.XMLInstance;
import cn.com.frame.common.util.ParamUtil;
import cn.com.frame.model.SfSRole;
import cn.com.frame.service.RoleManagerService;
import cn.com.frame.services.config.module.common.ConfigServicesRunning;
import cn.com.frame.services.config.service.ConfigServices;
import cn.com.frame.spring.SpringContextDB1;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2012-9-18
 */
public class PermissionsConfig extends ConfigServicesRunning {

	// 格式见包说明
	/**
	 * 构造函数默认
	 */
	public PermissionsConfig() {
		super(ParamUtil.CLASSES_PATH + ConfigServices.XML_ROLE_PERMISSION_PATH,
				ConfigServices.CONFIG_ROLE_PERMISSION);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 带文件参数的构造函数
	 * 
	 * @param filename
	 */
	public PermissionsConfig(String filename) {
		super(ParamUtil.CLASSES_PATH + filename,
				ConfigServices.CONFIG_ROLE_PERMISSION + filename);
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
		// 获取DAO
		RoleManagerService roleBuisness = (RoleManagerService) SpringContextDB1
				.getContext().getBean("roleManagerService");
		try {
			// 获取目录下所有文件
			File director = new File(configPath);
			File[] exsitsFile = director.listFiles();
			Map configMap = new HashMap();

			for (int n = 0; n < exsitsFile.length; n++) {
				try {
					File tempfile = exsitsFile[n];
					// 符合条件的xml文件
					if (tempfile.isFile()
							&& tempfile.getName().indexOf(".") != -1
							&& tempfile
									.getName()
									.substring(
											tempfile.getName().lastIndexOf("."))
									.equals(".xml")) {

						XMLInstance instance = Sys.getXmlTools().readConfig(
								configPath + "\\" + tempfile.getName());
						List<XMLInstance> childNode = instance.getChildNodes();
						String roleid = "";
						for (Iterator<XMLInstance> it = childNode.iterator(); it
								.hasNext();) {
							XMLInstance node = it.next();
							roleid = (String) node.getAttributeMap()
									.get("name");
							List permissionList = new ArrayList();
							for (Iterator<XMLInstance> its = node
									.getChildNodes().iterator(); its.hasNext();) {
								XMLInstance perssionsNode = its.next();
								Map tempMap = new HashMap();
								tempMap.put("key", perssionsNode
										.getAttributeMap().get("key"));
								tempMap.put("name", perssionsNode
										.getAttributeMap().get("name"));
								permissionList.add(tempMap);
							}
							configMap.put(roleid, permissionList);
						}
						// 与数据库同步
						if (roleid.equals("")) {
							continue;
						}
						List roleList = (List) roleBuisness
								.findByCondition( " roleid = '"
										+ roleid + "' ",0 ,0, null);
						if (roleList.size() <= 0) {
							// 存角色
							SfSRole role = new SfSRole();
							role.setRoleid(roleid);
							role.setUuid(Sys.getConverter().getUUID());
							role.setRolestate("1");
							roleBuisness.saveOrUpdate(role);
						}

					}
				} catch (Exception ex) {
					ex.printStackTrace();

				}
			}
			ConfigServices.intoConfig(configKey, configMap);
			System.out.println("读取角色权限配置文件成功");
			return configMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("读取角色权限配置文件失败-------------------");

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
			return false;
		}
		XMLInstance root = new XMLInstance();
		root.setNodeName("root");
		List<XMLInstance> childNode = new ArrayList<XMLInstance>();
		XMLInstance headChild = new XMLInstance();
		headChild.setNodeName("permissions");
		String filename = "";
		for (Iterator it = config.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			filename = key;
			XMLInstance xml = new XMLInstance();
			xml.setNodeName("roleid");
			Map<String, Object> attributeMap = new HashMap<String, Object>();
			attributeMap.put("name", key);
			xml.setAttributeMap(attributeMap);
			List permissionList = (List) config.get(key);
			List<XMLInstance> childNodeSub = new ArrayList<XMLInstance>();
			for (Iterator its = permissionList.iterator(); its.hasNext();) {
				Map mapvalue = (Map) its.next();
				XMLInstance tempxml = new XMLInstance();
				tempxml.setNodeName("permissions");
				attributeMap = new HashMap<String, Object>();
				attributeMap.put("key", mapvalue.get("key"));
				attributeMap.put("name", mapvalue.get("name"));
				tempxml.setAttributeMap(attributeMap);
				childNodeSub.add(tempxml);
			}
			xml.setChildNodes(childNodeSub);
			childNode.add(xml);
			break;
		}
		headChild.setChildNodes(childNode);
		childNode.add(headChild);
		childNode = new ArrayList<XMLInstance>();
		root.setChildNodes(childNode);
		try {
			if (!filename.equals("")) {
				Sys.getXmlTools().writeConfig(root,
						configPath + "\\" + filename);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
