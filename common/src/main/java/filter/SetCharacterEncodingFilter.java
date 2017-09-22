package filter;

import javax.servlet.*;
import java.io.IOException;

//@WebFilter(
//        filterName = "SetCharacterEncodingFilter",
//        urlPatterns = {"/*"},
//        initParams = {@WebInitParam(name = "encoding", value = "utf-8")})

// 创建自定义的编码过滤器
// 可以使用 @WebFilter 标记，这样引用了包的项目将会自动配置
// 也可以不指定此标记，所以需要显式在 Web.xml 中配置

// Spring 框架自动配置了编码，不需要此处进行显式编码指定
// 此处仅仅是 Demo
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
