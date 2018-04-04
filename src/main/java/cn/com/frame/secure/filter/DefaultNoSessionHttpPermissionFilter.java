package cn.com.frame.secure.filter;

import cn.com.frame.common.util.ParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Sirius on 2017/6/14.
 */
public class DefaultNoSessionHttpPermissionFilter implements Filter {

    private static Logger logger = null;

    @Override
    public void init(FilterConfig config) {
        logger = LoggerFactory.getLogger(DefaultNoSessionHttpPermissionFilter.class);
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(false);
        String url = request.getRequestURI();
        if (!url.equals(request.getContextPath()) && !url.equals(request.getContextPath() + "/")) {
            int i;
            for (i = 0; i < ParamUtil.commonType.length; ++i) {
                if (url.lastIndexOf(".") != -1 && url.substring(url.lastIndexOf(".") + 1).equals(ParamUtil.commonType[i])) {
                    chain.doFilter(req, response);
                    return;
                }
            }
            i = 0;
            while (true) {
                if (i >= ParamUtil.commonPage.length) {
                    for (i = 0; i < ParamUtil.commonAccess.length; ++i) {
                        if (url.indexOf(ParamUtil.commonAccess[i]) != -1) {
                            chain.doFilter(req, response);
                            return;
                        }
                    }
                    if (session != null && session.getAttribute("SYS_USER") != null) {
                        chain.doFilter(request, response);
                        return;
                    }

                    logger.info("----敏感监测----匿名用户访问地址：" + url + "；IP地址：" + request.getRemoteAddr() + "；服务：" + request.getContextPath() + "；服务器地址：" + request.getServerName() + "；协议：" + request.getProtocol() + "；调用类型：" + request.getMethod());
//                    response.getOutputStream().write("sessionout".getBytes());
                    ((HttpServletResponse) response).sendRedirect(request.getContextPath() + "/pages/module/welcome/login.jsp");
                    return;
                }

                if (url.indexOf("?") != -1) {
                    if (url.substring(0, url.indexOf("?")).equals(ParamUtil.commonPage[i])) {
                        break;
                    }
                } else if (url.equals(ParamUtil.commonPage[i])) {
                    break;
                }
                ++i;
            }
            chain.doFilter(req, response);
        } else {
            chain.doFilter(req, response);
        }
    }

    @Override
    public void destroy() {
        logger = null;
    }
}
