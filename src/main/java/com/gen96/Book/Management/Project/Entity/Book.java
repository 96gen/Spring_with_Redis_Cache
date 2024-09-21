package com.gen96.Book.Management.Project.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id//表示這個是id也是主鍵
    @GeneratedValue(strategy = GenerationType.IDENTITY)//會在資料庫添加AUTO_INCREMENT
    private Long id;
    private String name;
    private int page;

    public Book(){

    }

    public Book(Long id, String name, int page) {
        this.id = id;
        this.name = name;
        this.page = page;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
