package com.lebilotta.pubsubmessaging.service;

import com.lebilotta.pubsubmessaging.domain.Book;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ConsumerMessageService {

    private final BookService bookService;

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    public ConsumerMessageService(BookService bookService) {
        this.bookService = bookService;
    }


    @SqsListener(value = "${aws.sqs.register-book-url}")
    public void receiveMessage(Book book) {
        log.info("CONSUMER SERVICE - receiveMessage - Nova mensagem recebida: {}", book);

        bookService.saveBookRegister(book);

        log.info("CONSUMER SERVICE - receiveMessage - Mensagem recebida com sucesso");
    }
}
