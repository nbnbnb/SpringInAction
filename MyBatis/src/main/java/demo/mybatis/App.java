package demo.mybatis;

import demo.mybatis.config.H2Config;
import demo.mybatis.repo.BlogRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(H2Config.class);
        BlogRepository blogRepository = context.getBean(BlogRepository.class);
        blogRepository.readBlog();
    }

}
