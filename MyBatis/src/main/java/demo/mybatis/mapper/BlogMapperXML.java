package demo.mybatis.mapper;

import demo.mybatis.entity.Blog;

public interface BlogMapperXML {
    // 此处使用的是 XML 配置
    // 所以不需要 @Select 进行 SQL 注入
    Blog selectBlog(int id);
}
