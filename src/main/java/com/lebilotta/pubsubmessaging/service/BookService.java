package com.lebilotta.pubsubmessaging.service;

import com.lebilotta.pubsubmessaging.domain.Book;
import com.lebilotta.pubsubmessaging.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private final ProducerMessageService producerMessageService;

    private final BookRepository bookRepository;

    public BookService(ProducerMessageService producerMessageService, BookRepository bookRepository) {
        this.producerMessageService = producerMessageService;
        this.bookRepository = bookRepository;
    }

    public void sendRegisterBookMessage(Book book) {
        log.info("SERVICE - sendRegisterBookMessage - Enviando mensagem: {}", book);

        producerMessageService.sendMessage(book);

        log.info("SERVICE - sendRegisterBookMessage - Mensagem enviada");
    }

    public void saveBookRegister(Book book) {
        log.info("SERVICE - saveBookRegister - Salvando registro: {}", book);

        bookRepository.save(book);

        log.info("SERVICE - saveBookRegister - Registro salvo com sucesso");
    }
}
