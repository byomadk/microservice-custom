package com.example.custom.controller;

import com.example.custom.dto.request.BookRequestDto;
import com.example.custom.dto.response.BookResponseDto;
import com.example.custom.dto.response.ResponseStatusOnlyDto;
import com.example.custom.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("${app.context-path:/${spring.application.name}}${app.public-prefix-path}/books")
@RequestMapping("/library/books")
public class BookController extends ExceptionController{

    @Autowired
    BookService bookService;

    @GetMapping("/getlist")
    public ResponseEntity<List<BookResponseDto>> getBookList() throws Exception {
        List<BookResponseDto> responseDto = bookService.getBookList();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<BookResponseDto> getBookDetail(@PathVariable String id) throws Exception {
        BookResponseDto responseDto = bookService.getBookDetail(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseStatusOnlyDto> addBook(@RequestBody BookRequestDto bookRequestDto) throws Exception {
        ResponseStatusOnlyDto responseDto = bookService.addBook(bookRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<ResponseStatusOnlyDto> editBook(@PathVariable String id) throws Exception {
        ResponseStatusOnlyDto responseDto = bookService.editBook(new BookRequestDto(id));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ResponseStatusOnlyDto> deleteBook(@PathVariable String id) throws Exception {
        ResponseStatusOnlyDto responseDto = bookService.deleteBook(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/search/{searchValue}")
    public ResponseEntity<List<BookResponseDto>> getBookBySearch(@PathVariable String searchValue) throws Exception {
        List<BookResponseDto> responseDto = bookService.getBookBySearch(searchValue);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
