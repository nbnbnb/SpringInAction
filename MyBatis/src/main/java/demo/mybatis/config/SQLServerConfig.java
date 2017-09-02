package demo.mybatis.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@Import(AppConfig.class)
public class SQLServerConfig {
    @Bean
    public DataSource dataSource() {

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
}
