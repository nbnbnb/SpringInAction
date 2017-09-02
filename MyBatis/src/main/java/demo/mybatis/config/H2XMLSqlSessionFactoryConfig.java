package demo.mybatis.config;

import demo.mybatis.repo.BlogRepositoryXML;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.io.InputStream;

@ComponentScan(basePackageClasses = {BlogRepositoryXML.class})
public class H2XMLSqlSessionFactoryConfig {
    @Bean
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config-sqlserver.xml";  // 从 XML 文件中获取 DataSource 数据
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
