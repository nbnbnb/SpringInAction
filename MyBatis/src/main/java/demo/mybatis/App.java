package demo.mybatis;

import demo.mybatis.config.H2DataSourceConfig;
import demo.mybatis.config.H2XMLSqlSessionFactoryConfig;
import demo.mybatis.config.SQLServerXMLSqlSessionFactoryConfig;
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
        ApplicationContext context = new AnnotationConfigApplicationContext(H2DataSourceConfig.class);
        BlogRepository blogRepository = context.getBean(BlogRepository.class);
        blogRepository.readBlog();
    }

    private static void sqlserver() {
        ApplicationContext context = new AnnotationConfigApplicationContext(H2DataSourceConfig.class);
        BlogRepository blogRepository = context.getBean(BlogRepository.class);
        blogRepository.readBlog();
    }

    private static void sqlserverWithXml() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SQLServerXMLSqlSessionFactoryConfig.class);
        BlogRepositoryXML blogRepository = context.getBean(BlogRepositoryXML.class);
        blogRepository.readBlog();
    }

    private static void h2WithXml() {
        ApplicationContext context = new AnnotationConfigApplicationContext(H2XMLSqlSessionFactoryConfig.class);
        BlogRepositoryXML blogRepository = context.getBean(BlogRepositoryXML.class);
        blogRepository.readBlog();
    }
}
