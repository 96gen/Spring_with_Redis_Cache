package com.gen96.Book.Management.Project.Repository;

import com.gen96.Book.Management.Project.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

//Book是我們的entity，Long是id的資料型態。
public interface BookRepository extends JpaRepository<Book, Long> {
}
