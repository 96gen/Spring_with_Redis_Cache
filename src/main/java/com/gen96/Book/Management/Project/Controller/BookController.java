package com.gen96.Book.Management.Project.Controller;

import com.gen96.Book.Management.Project.Entity.Book;
import com.gen96.Book.Management.Project.Service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//BookController中的所有網址的開頭都是/api/book
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //新增book，並將新增的book資訊回傳
    @PostMapping("/")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }

    //將資料庫中所有的book回傳
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    //將指定的book內容回傳
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    //修改指定的book內容
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        book.setId(id);
        return ResponseEntity.ok(bookService.updateBook(book));
    }

    //將指定的book刪除
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok("Book deleted successfully");
    }
}
