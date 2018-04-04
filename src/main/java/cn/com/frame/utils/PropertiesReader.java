/**
 * 作者：rwj31
 * 时间：03/11/2017
 * 项目：office
 *
 * @param
 * @return 说明：
 */

package cn.com.frame.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    public void PropertiesReader() {

    }
    public Properties getProperties(String propertiesName) {
        InputStream input = null;
        try {
            input = this.getClass().getResourceAsStream("/" + propertiesName);
            Properties properties = new Properties();
            properties.load(input);
//            String pagestring = properties.getProperty("permission");
//            String[] split = pagestring.split(",");
//            ParamUtil.commonPage = split;
//            pagestring = properties.getProperty("action");
//            split = pagestring.split(",");
//            ParamUtil.commonAccess = split;
//            pagestring = properties.getProperty("tail");
//            split = pagestring.split(",");
//            ParamUtil.commonType = split;
            return properties;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
