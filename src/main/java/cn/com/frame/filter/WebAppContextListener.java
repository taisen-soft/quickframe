package cn.com.frame.filter;

import cn.com.frame.common.builder.Sys;
import cn.com.frame.common.builder.SystemBuilder;
import cn.com.frame.common.tools.file.PropertiesReader;
import cn.com.frame.common.tools.reflect.SystemReflect;
import cn.com.frame.common.tools.xml.XMLInstance;
import cn.com.frame.common.util.ParamUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class WebAppContextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent event) {
        // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent event) {
        // TODO Auto-generated method stub
        SystemReflect systemReflect = new SystemReflect();
        ServletContext application = event.getServletContext();

        try {
            /**
             * 1、读取系统变量
             */
            // 读取系统配置
            // 生成器初始化
            System.out.println("设置系统变量。");
            SystemBuilder.getBuilder().getToolsFactory().getJsonTools();
            SystemBuilder.getBuilder().getToolsFactory().getXMLTools();
            SystemBuilder.getBuilder().getManagersFactory().getUtilManager();
            // 变量管理初始化
            ParamUtil.init();
            // 变量初始化
            Properties prop = System.getProperties();
            String os = prop.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                ParamUtil.FILE_SEPRATOR = "/";
            } else {
                ParamUtil.FILE_SEPRATOR = "//";
            }
            try {
                if (os.contains("win")) {
                    ParamUtil.CLASSES_PATH = this.getClass().getResource("/")
                            .toString().replaceAll("%20", " ").substring(6);
                } else {
                    ParamUtil.CLASSES_PATH = this.getClass().getResource("/")
                            .toString().replaceAll("%20", " ").substring(5);
                }
            } catch (Exception e) {
                e.printStackTrace();
                ParamUtil.CLASSES_PATH = "";
            }
            ParamUtil.WEBROOT_PATH = event.getServletContext().getRealPath("/");

            /**
             * 2、读取反射位置
             */
            // 反射位置
            // 默认反射位置
            String[] tableArr = new String[]{"cn.com.frame.model.SfBCommon","cn.com.frame.model.SfBCommonChild", "cn.com.frame.model.SfBConfig", "cn.com.frame.model.SfBMessage"
                    , "cn.com.frame.model.SfBQuickcommon", "cn.com.frame.model.SfBSmallcommon", "cn.com.frame.model.SfSDatacode"
                    , "cn.com.frame.model.SfSGroup", "cn.com.frame.model.SfSGroupRole", "cn.com.frame.model.SfSLog", "cn.com.frame.model.SfSRole"
                    , "cn.com.frame.model.SfSRolePerm", "cn.com.frame.model.SfSUser", "cn.com.frame.model.SfSUserGroup", "cn.com.frame.model.SfSUserRole"};
            systemReflect.reflect(tableArr);

            XMLInstance instance = Sys.getXmlTools().readConfig(this.getClass().getResource("/config/sysloader/sys_reflect.xml").getFile().substring(1));
            for (int i = 0; i < instance.getChildNodes().size(); i++) {
                XMLInstance xmlins = (XMLInstance) instance.getChildNodes().get(i);
                String attr = (String) xmlins.getAttributeMap().get("classname");
                String[] reflectDir;
                if (attr.substring(attr.lastIndexOf(".")).equals(".*")) {
                    String[] wholePackage = attr.substring(0,
                            attr.length() - 2).split("[.]");
                    StringBuffer dirString = new StringBuffer("");
                    for (int j = 0; j < wholePackage.length; j++) {
                        dirString.append(ParamUtil.FILE_SEPRATOR
                                + wholePackage[j]);
                    }
                    File fileDir = new File(dirString.toString());

                    String[] tempDir = fileDir.list();
                    reflectDir = new String[tempDir.length];
                    for (int j = 0; j < tempDir.length; j++) {
                        reflectDir[j] = attr.substring(0,
                                attr.length() - 2)
                                + "."
                                + tempDir[j]
                                .substring(
                                        0,
                                        tempDir[j]
                                                .lastIndexOf(".") < 0 ? tempDir[j]
                                                .length()
                                                : tempDir[j]
                                                .indexOf("."));
                    }
                    systemReflect.reflect(reflectDir);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 3、权限限制页面读取
         */
        // 读取权限限制页面
        new PropertiesReader().init();

        /**
         * 4、读取系统内存配置
         */
        try {
            XMLInstance instance = Sys.getXmlTools().readConfig(this.getClass().getResource("/config/sysloader/sys_config.xml").getFile().substring(1));
            List<XMLInstance> childNode = instance.getChildNodes();
            Map configMap = new HashMap();
            String sysname = "";
            String sysid = "";
            String syscom = "";
            String admin_u = "";
            String admin_p = "";
            List resultList = new ArrayList();
            for (Iterator<XMLInstance> it = childNode.iterator(); it.hasNext(); ) {
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
            System.out.println("系统配置文件读取成功：系统名称“" + sysname + "”，系统编码“" + sysid + "”，版权＂" + syscom + "＂");
            application.setAttribute("SYS_CH_NAME", ParamUtil.SYSTEM_NAME);
            application.setAttribute("SYS_ID_NAME", ParamUtil.SYSTEM_ID);
            application.setAttribute("SYS_ORG_COM", ParamUtil.SYSTEM_ORG_COM);

            //读取配置
            Properties properties = new cn.com.frame.utils.PropertiesReader().getProperties("sysconfig.properties");
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(properties);
            event.getServletContext().setAttribute("sysconfig", concurrentHashMap);
            try {
//            sysconfig.sessionOutPage=/pages/module/welcome/login.jsp
//            sysconfig.innerUser=admin
//            sysconfig.innerPwd=123456
//            sysconfig.systemName=山西省人民防空办公室无纸化办公系统
//            sysconfig.developer=山西泰森科技股份有限公司
//            sysconfig.version=v1.0
                event.getServletContext().setAttribute("systemName", new String(((String) (concurrentHashMap.get("sysconfig.systemName"))).getBytes("ISO-8859-1"), "UTF-8"));
                event.getServletContext().setAttribute("developer", new String(((String) (concurrentHashMap.get("sysconfig.developer"))).getBytes("ISO-8859-1"), "UTF-8"));
                event.getServletContext().setAttribute("sessionOutPage", new String(((String) (concurrentHashMap.get("sysconfig.sessionOutPage"))).getBytes("ISO-8859-1"), "UTF-8"));
                event.getServletContext().setAttribute("innerUser", new String(((String) (concurrentHashMap.get("sysconfig.innerUser"))).getBytes("ISO-8859-1"), "UTF-8"));
                event.getServletContext().setAttribute("innerPwd", new String(((String) (concurrentHashMap.get("sysconfig.innerPwd"))).getBytes("ISO-8859-1"), "UTF-8"));
                event.getServletContext().setAttribute("version", new String(((String) (concurrentHashMap.get("sysconfig.version"))).getBytes("ISO-8859-1"), "UTF-8"));
//                event.getServletContext().setAttribute("signServer", new String(((String) (concurrentHashMap.get("sysconfig.signServer"))).getBytes("ISO-8859-1"), "UTF-8"));

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
