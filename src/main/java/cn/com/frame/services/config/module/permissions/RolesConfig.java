/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.services.config.module.permissions;

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

/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2012-9-18
 */
public class RolesConfig extends ConfigServicesRunning {


    // 格式见包说明
    /**
     * 构造函数默认
     */
    public RolesConfig() {
        super(ParamUtil.CLASSES_PATH + ConfigServices.XML_ROLE_PERMISSION,
                ConfigServices.CONFIG_ROLE_PERMISSION);
        // TODO Auto-generated constructor stub
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
            for (Iterator<XMLInstance> it = childNode.iterator(); it.hasNext();) {
                XMLInstance node = it.next();
                List permissionList = new ArrayList();
                for (Iterator<XMLInstance> its = node.getChildNodes()
                        .iterator(); its.hasNext();) {
                    XMLInstance perssionsNode = its.next();
                    Map tempMap = new HashMap();
                    tempMap.put("key",
                            perssionsNode.getAttributeMap().get("key"));
                    tempMap.put("name",
                            perssionsNode.getAttributeMap().get("name"));
                    permissionList.add(tempMap);
                }
                configMap.put(node.getAttributeMap().get("name"),
                        permissionList);
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
            config = (Map) ConfigServices
                    .findConfigByKey(ConfigServices.CONFIG_ROLE_PERMISSION);
        }
        XMLInstance root = new XMLInstance();
        root.setNodeName("root");
        List<XMLInstance> childNode = new ArrayList<XMLInstance>();
        XMLInstance headChild = new XMLInstance();
        headChild.setNodeName("permissions");
        for (Iterator it = config.keySet().iterator(); it.hasNext();) {
            String key = (String) it.next();
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.system.services.config.module.common.ConfigModel#syncConfig(java.
     * lang.Object)
     */
    @Override
    public void syncConfig(Object config) {
        // TODO Auto-generated method stub
        ConfigServices.intoConfig(super.configKey, config);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.system.services.common.AbstractServices#servicesStart(java.util.Map)
     */
    @Override
    public void servicesStart() {
        // TODO Auto-generated method stub
        if (RUNNING_PARAMS == null) {
            this.readConfig();
        } else if (RUNNING_PARAMS.get("type") != null ? ((String) RUNNING_PARAMS
                .get("type")).equals("write") : false) {
            if (RUNNING_PARAMS.get("config") != null) {
                this.writeConfig((Map)RUNNING_PARAMS.get("config"));
            }else{
                this.writeConfig(null);
            }
        }

    }
}
