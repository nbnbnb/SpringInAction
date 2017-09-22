package demo.mybatis.mapper;

import demo.mybatis.entity.Blog;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface BlogMapper {
    @Select("SELECT * FROM blog WHERE id = #{id}")

    // 如果表中的字段和实体字段名称不一致，可以使用下面的映射
    // 如果是 XML 则是通过 resultType 进行映射
    @Results({
            @Result(property = "postedTime", column = "postedTime")
    })
    Blog selectBlog(int id);
}
