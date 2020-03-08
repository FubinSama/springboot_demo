package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {
    @Autowired
    BookDao bookDao;

    @Test
    public void contextLoads() {
        bookDao.getBookById1(1);
        bookDao.getBookById1(1);
        bookDao.getBookById2(2);
        bookDao.getBookById2(2);
        bookDao.deleteBookById(1);
        Book b3 = bookDao.getBookById1(1);
        System.out.println("b3: " + b3);
        Book b = new Book();
        b.setName("三国演义");
        b.setAuthor("罗贯中");
        b.setId(1);
        bookDao.updateBookById(b);
        Book b4 = bookDao.getBookById1(1);
        System.out.println("b4: " + b);
    }
}
