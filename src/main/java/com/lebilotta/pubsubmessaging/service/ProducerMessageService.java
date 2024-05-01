package com.lebilotta.pubsubmessaging.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lebilotta.pubsubmessaging.domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProducerMessageService {

    @Value("${aws.sqs.register-book-url}")
    private String queueUrl;

    final AmazonSQS sqs;

    final ObjectMapper mapper;

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    public ProducerMessageService() {
        this.sqs = AmazonSQSClientBuilder.defaultClient();
        this.mapper = new ObjectMapper();
    }

    public void sendMessage(Book book) {
        log.info("PRODUCER SERVICE - sendMessage - REQUEST: {}", book);

        var sendMessageRequest = new SendMessageRequest()
            .withQueueUrl(this.queueUrl)
            .withMessageBody(this.convertMessageBodyToString(book));

        sqs.sendMessage(sendMessageRequest);

        log.info("PRODUCER SERVICE - sendMessage - RESPONSE: SUCESSO");
    }

    private String convertMessageBodyToString(Book book) {
        log.info("PRODUCER SERVICE - convertMessageBodyToString - Iniciando conversão do body");

        try {
            var convertedBody = this.mapper.writeValueAsString(book);
            log.info("PRODUCER SERVICE - convertMessageBodyToString - Body convertido com sucesso");
            return convertedBody;
        } catch (JsonProcessingException e) {
            log.error("PRODUCER SERVICE - convertMessageBodyToString - Ocorreu um erro ao converter o body");
            throw new RuntimeException(e);
        }
    }
}
