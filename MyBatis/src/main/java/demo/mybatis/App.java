package demo.mybatis;

import demo.mybatis.config.AppConfig;

import demo.mybatis.repo.BlogRepository;
import demo.mybatis.repo.BlogRepositoryXML;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        h2();
        sqlserver();
        sqlserverWithXml();
        h2WithXml();
    }

    private static void h2() {
        System.setProperty("spring.profiles.active", "h2");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BlogRepository blogRepository = context.getBean(BlogRepository.class);
        blogRepository.readBlog();
    }

    private static void sqlserver() {
        System.setProperty("spring.profiles.active", "sqlserver");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getEnvironment().acceptsProfiles("sqlserver");
        BlogRepository blogRepository = context.getBean(BlogRepository.class);
        blogRepository.readBlog();
    }

    private static void sqlserverWithXml() {
        System.setProperty("spring.profiles.active", "sqlserverxml");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BlogRepositoryXML blogRepository = context.getBean(BlogRepositoryXML.class);
        blogRepository.readBlog();
    }

    private static void h2WithXml() {
        System.setProperty("spring.profiles.active", "h2xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BlogRepositoryXML blogRepository = context.getBean(BlogRepositoryXML.class);
        blogRepository.readBlog();
    }
}
