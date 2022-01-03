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

    protected String encoding = "utf-8";
    protected FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (encoding != null) {
            HttpServletRequest req = (HttpServletRequest) request;
            StringBuffer requestURL = req.getRequestURL();
            if (requestURL.toString().contains(".")) {
                String substring = requestURL.substring(requestURL.lastIndexOf("."), requestURL.length());
                System.out.println(requestURL + "----------" + substring);
                if (substring.equals("png")) {
                    chain.doFilter(request, response);
                }
            }
            request.setCharacterEncoding(encoding);
            response.setContentType("text/html; charset=" + encoding);

        }
        chain.doFilter(request, response);
    }

    public void destroy() {
        this.encoding = null;
        this.filterConfig = null;
    }
}
