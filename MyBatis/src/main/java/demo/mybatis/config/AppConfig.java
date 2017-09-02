package demo.mybatis.config;

import demo.mybatis.mapper.BlogMapper;
import demo.mybatis.repo.BlogRepository;
import demo.mybatis.repo.BlogRepositoryXML;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

public class AppConfig {
    @Bean
    @Profile("h2")
    public DataSource dataSourceForH2() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("classpath:schema.sql", "classpath:test-data.sql")
                .build();
    }

    @Bean
    @Profile("sqlserver")
    public DataSource dataSourceForSQLServer() {

        // 使用数据源连接池，可选方案
        /*
            Apache Commons DBCP (http://jakarta.apache.org/commons/dbcp)
            c3p0 (http://sourceforge.net/projects/c3p0/)
            BoneCP (http://jolbox.com/)
        */

        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        ds.setUrl("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=JavaTempSolution;IntegratedSecurity=True");
        ds.setUsername("sa");
        ds.setPassword("");
        ds.setInitialSize(5);   // 初始化连接数
        ds.setMaxTotal(10);     // 最大连接数
        return ds;
    }

    @Bean
    @Profile({"h2", "sqlserver"})
    public SqlSessionFactory getSqlSessionFactory(DataSource dataSource) {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(BlogMapper.class);  // 设置 MyBatis Mapper 路径
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }

    // 下面的 XML 配置中已经包含了 DataSource 设置
    // SqlSessionFactory 依赖 XML 中的 DataSource 进行装配
    @Bean
    @Profile("h2xml")
    public SqlSessionFactory getSqlSessionFactoryForH2() throws IOException {
        String resource = "mybatis-config-sqlserver.xml";  // 从 XML 文件中获取 DataSource 数据
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Bean
    @Profile("sqlserverxml")
    public SqlSessionFactory getSqlSessionFactoryForSQLServer() throws IOException {
        String resource = "mybatis-config-sqlserver.xml";  // 从 XML 文件中获取 DataSource 数据
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


    @Bean
    @Profile({"h2", "sqlserver"})
    public BlogRepository blogRepository(){
        return new BlogRepository();
    }

    @Bean
    @Profile({"h2xml", "sqlserverxml"})
    public BlogRepositoryXML blogRepositoryXML(){
        return new BlogRepositoryXML();
    }
}
