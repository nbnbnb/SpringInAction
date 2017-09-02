package demo.mybatis.mapper;

import demo.mybatis.entity.Blog;
import org.apache.ibatis.annotations.Select;

public interface BlogMapper {
    @Select("SELECT * FROM blog WHERE id = #{id}")
    Blog selectBlog(int id);
}
