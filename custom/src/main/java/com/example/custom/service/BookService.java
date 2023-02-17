package com.example.custom.service;

import com.example.custom.dto.request.BookRequestDto;
import com.example.custom.dto.response.BookResponseDto;
import com.example.custom.dto.response.ResponseStatusOnlyDto;
import com.example.custom.entity.BookEntity;

import java.util.List;

public interface BookService {

    public List<BookResponseDto> getBookList() throws Exception;
    public BookResponseDto getBookDetail(String id) throws Exception;
    public ResponseStatusOnlyDto addBook(BookRequestDto requestDto) throws Exception;
    public ResponseStatusOnlyDto editBook(BookRequestDto requestDto) throws Exception;
    public ResponseStatusOnlyDto deleteBook(String id) throws Exception;
    public List<BookResponseDto> getBookBySearch(String searchValue) throws Exception;
}
