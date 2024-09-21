package com.gen96.Book.Management.Project.Service;

import com.gen96.Book.Management.Project.Entity.Book;
import com.gen96.Book.Management.Project.Repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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
        Optional<Book> opt = bookRepository.findById(id);
        return opt.orElse(null);
    }

    //根據提供的id編號修改對應的book
    public Book updateBook(Book book) {
        Optional<Book> opt = bookRepository.findById(book.getId());
        if (opt.isPresent()) {
            Book updatedBook = opt.get();
            updatedBook.setName(book.getName() == null ? updatedBook.getName() : book.getName());
            updatedBook.setPage(book.getPage());
            return bookRepository.save(updatedBook);
        }
        return null;
    }

    //根據提供的id編號在資料庫中刪除book
    public void deleteBookById(Long id) {
        Optional<Book> opt = bookRepository.findById(id);
        opt.ifPresent(book -> bookRepository.deleteById(book.getId()));
    }
}
