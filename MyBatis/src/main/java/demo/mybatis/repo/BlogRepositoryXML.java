package demo.mybatis.repo;

import demo.mybatis.entity.Blog;
import demo.mybatis.mapper.BlogMapperXML;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepositoryXML {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public void readBlog() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BlogMapperXML mapper = session.getMapper(BlogMapperXML.class);
            Blog blog = mapper.selectBlog(5);
            System.out.println(blog.content);
        } finally {
            session.close();
        }
    }
}
