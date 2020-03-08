package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@CacheConfig(cacheNames = "book_cache")
public class BookDao {
    @Cacheable //默认使用方法的参数作为key，返回值作为value。一般加在查询方法上
    //当缓存中有该数据时，不会执行方法，没有时执行方法，并将返回值缓存
    public Book getBookById1(Integer id) {
        System.out.println("getBookById1");
        Book book = new Book();
        book.setId(id);
        book.setName("三国演义1");
        book.setAuthor("罗贯中1");
        return book;
    }

    @CachePut(key = "#book.id") //一般加载更新方法上，该方法一定会执行，并重新进行缓存
    public Book updateBookById(Book book){
        System.out.println("updateBookById");
        book.setName("三国演义2");
        return book;
    }

    @CacheEvict(key = "#id") //一般加载删除方法上，同时删除缓存中的该数据
    public void deleteBookById(Integer id){
        System.out.println("deleteBookById");
    }

    @Autowired
    MyKeyGenerator myKeyGenerator;
    @Cacheable(keyGenerator = "myKeyGenerator")
    public Book getBookById2(Integer id) {
        System.out.println("getBookById2");
        Book book = new Book();
        book.setId(id);
        book.setName("三国演义2");
        book.setAuthor("罗贯中2");
        return book;
    }
}
