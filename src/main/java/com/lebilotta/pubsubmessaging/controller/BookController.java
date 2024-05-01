package com.lebilotta.pubsubmessaging.controller;

import com.lebilotta.pubsubmessaging.domain.Book;
import com.lebilotta.pubsubmessaging.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class BookController {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(
        value = "/book",
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Void> registerNewBook(@RequestBody Book book) {
        log.info("CONTROLLER - registerNewBook - REQUEST: {}", book);
        this.bookService.sendRegisterBookMessage(book);
        log.info("CONTROLLER - registerNewBook - RESPONSE: SUCESSO");
        return ResponseEntity.ok().build();
    }
}
