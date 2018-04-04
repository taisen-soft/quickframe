//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.com.frame.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class CustomerRequestLogFilter extends AbstractFilter {
    private static Logger logger = null;

    public CustomerRequestLogFilter() {
    }

    public void init(FilterConfig config) {
        logger = LoggerFactory.getLogger(CustomerRequestLogFilter.class);
    }

    public void destroy() {
        logger = null;
    }

    public static String getParamsString(Map<String, String[]> params) {
        if (params != null && !params.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            builder.append("?");
            Iterator var2 = params.keySet().iterator();

            while(var2.hasNext()) {
                String key = (String)var2.next();
                builder.append(key).append("=").append(((String[])params.get(key))[0]).append("&");
            }

            builder.deleteCharAt(builder.lastIndexOf("&"));
            return builder.toString();
        } else {
            return "";
        }
    }

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain, HttpSession session, String menthod, String url) throws IOException, ServletException {
//        logger.info("Accept:{}", request.getHeader("Accept"));
//        logger.info("Content-Type:{}", request.getHeader("Content-Type"));
        logger.info("------开始处理请求--------");
//        long before = System.currentTimeMillis();
        logger.info("收到请求:{} : {}{}", new Object[]{menthod, url, getParamsString(request.getParameterMap())});
        chain.doFilter(request, response);
//        long after = System.currentTimeMillis();
        logger.info("请求结果:" + url + " status:" + response.getStatus());
//        logger.info("花费时间：" + (after - before) + "ms");
        logger.info("------请求处理结束---------\n");
    }
}
