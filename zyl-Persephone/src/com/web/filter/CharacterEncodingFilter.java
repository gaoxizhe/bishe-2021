package com.web.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 字符编码过滤器
 *
 * @author 王佳祺
 */
public class CharacterEncodingFilter implements Filter {

    protected String encoding = null;
    protected FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
//        this.encoding = filterConfig.getInitParameter("encoding");
        this.encoding = "utf-8";
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (encoding != null) {
            HttpServletRequest req = (HttpServletRequest) request;
            StringBuffer requestURL = req.getRequestURL();
            // 获取图片验证码
            if (requestURL.toString().contains("imageCode")) {
                chain.doFilter(request, response);
                return;
            }
            if (requestURL.toString().contains(".")) {
                String substring = requestURL.substring(requestURL.lastIndexOf("."), requestURL.length());
                System.out.println(requestURL + "----------" + substring);
                if (".png".equals(substring)
                        || ".jpg".equals(substring)
                        || ".js".equals(substring)
                        || ".css".equals(substring)
                        || ".woff2".equals(substring)
                ) {
                    chain.doFilter(request, response);
                    return;
                }
            }
            request.setCharacterEncoding(encoding);
            response.setContentType("text/html; charset=" + encoding);

        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        this.encoding = null;
        this.filterConfig = null;
    }
}
