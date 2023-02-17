package com.example.custom.dao;

import com.example.custom.entity.BookEntity;

import java.util.List;

public interface BookDao {
    public List<BookEntity> getBookList() throws Exception;
    public BookEntity getBookDetail(String id) throws Exception;
    public BookEntity addBook(BookEntity book) throws Exception;
    public BookEntity editBook(BookEntity book) throws Exception;
    public Boolean deleteBook(String id) throws Exception;
    public List<BookEntity> getBookBySearch(String searchValue, Integer searchValueInt) throws Exception;
}
