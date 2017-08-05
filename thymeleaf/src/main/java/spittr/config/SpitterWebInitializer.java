package spittr.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import spittr.web.WebConfig;

public class SpitterWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // getRootConfigClasses() 方法返回的带有 @Configuration 注解的类将会用来配置 ContextLoaderListener 创建的应用上下文中的 bean。
        // ContextLoaderListener 要加载应用中的其他 bean， 这些 bean 通常是驱动应用后端的中间层和数据层组件。
        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // getServletConfigClasses() 方法中， 我们要求 DispatcherServlet 加载应用上下文时， 使用定义在 WebConfig 配置类（ 使用 Java 配置） 中的 bean。
        // getServletConfigClasses() 方法返回的带有 @Configuration 注解的类将会用来定义 DispatcherServlet 应用上下文中的bean。

        // DispatcherServlet 加载包含 Web 组件的 bean， 如控制器、 视图解析器以及处理器映射，
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}