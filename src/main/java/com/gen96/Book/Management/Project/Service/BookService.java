package com.gen96.Book.Management.Project.Service;

import com.gen96.Book.Management.Project.Entity.Book;
import com.gen96.Book.Management.Project.Repository.BookRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String BOOK_CACHE = "BookCache";

    public BookService(BookRepository bookRepository, RedisTemplate<String, Object> redisTemplate) {
        this.bookRepository = bookRepository;
        this.redisTemplate = redisTemplate;
    }

    //將從controller取得的book，儲存到資料庫中，把資料庫儲存後的結果回傳給controller
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    //取得資料庫中所有的book，回傳給controller
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    //根據提供的id編號在資料庫中尋找book
    public Book getBookById(Long id) {
        String cacheKey = BOOK_CACHE + ":" + id;
        //根據cacheKey在Redis中找尋有沒有Cache
        Book cachedBook = (Book) redisTemplate.opsForValue().get(cacheKey);
        if (cachedBook != null) {
            System.out.println("-----From Cache----: " + cacheKey);//在Console顯示資料來自快取
            return cachedBook;
        }

        //如果Cache miss，從資料庫取得Book
        Optional<Book> opt = bookRepository.findById(id);
        //Book存在，將Book的資料加入Cache
        if (opt.isPresent()) {
            redisTemplate.opsForValue().set(cacheKey, opt.get(), 30, TimeUnit.SECONDS);//設定30秒後過期
            return opt.get();
        }
        return null;
    }

    //根據提供的id編號修改對應的book
    public Book updateBook(Book book) {
        Optional<Book> opt = bookRepository.findById(book.getId());
        if (opt.isPresent()) {
            Book updatedBook = opt.get();
            updatedBook.setName(book.getName() == null ? updatedBook.getName() : book.getName());
            updatedBook.setPage(book.getPage());
            String idString = String.valueOf(book.getId());
            //Book資料被變更，刪除過時的Cache
            redisTemplate.delete(BOOK_CACHE + ":" + book.getId());
            return bookRepository.save(updatedBook);
        }
        return null;
    }

    //根據提供的id編號在資料庫中刪除book
    public void deleteBookById(Long id) {
        Optional<Book> opt = bookRepository.findById(id);
        opt.ifPresent(book -> bookRepository.deleteById(book.getId()));
        String idString = String.valueOf(id);
        //Book資料被刪除，刪除過時的Cache
        redisTemplate.delete(BOOK_CACHE + ":" + id);
    }
}
