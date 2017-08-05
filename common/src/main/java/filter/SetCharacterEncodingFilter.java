package filter;

import javax.servlet.*;
import java.io.IOException;

//@WebFilter(
//        filterName = "SetCharacterEncodingFilter",
//        urlPatterns = {"/*"},
//        initParams = {@WebInitParam(name = "encoding", value = "utf-8")})
// 显式在 Web.xml 中配置
public class SetCharacterEncodingFilter implements Filter {

    private String encoding = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Using SetCharacterEncodingFilter " + encoding);
        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        this.encoding = null;
    }
}
