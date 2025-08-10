package com.group.libraryapp.controller.book;

import com.group.libraryapp.service.book.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService){ // 생성자를 통해서 스프링 컨테이너가 대신 BookService를 넣어줌
        this.bookService=bookService;
    }

    @PostMapping("/book")
    public void saveBook() {
        bookService.saveBook();
    }

}
