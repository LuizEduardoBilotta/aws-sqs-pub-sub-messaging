package com.lebilotta.pubsubmessaging.config;

import io.awspring.cloud.sqs.config.SqsMessageListenerContainerFactory;
import io.awspring.cloud.sqs.listener.QueueNotFoundStrategy;
import io.awspring.cloud.sqs.listener.acknowledgement.handler.AcknowledgementMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.time.Duration;

@Configuration
public class SqsConfig {
    @Bean
    SqsMessageListenerContainerFactory<Object> defaultSqsListenerContainerFactory(SqsAsyncClient sqsAsyncClient) {
        return SqsMessageListenerContainerFactory
            .builder()
            .configure(options -> options
                .queueNotFoundStrategy(QueueNotFoundStrategy.FAIL)
                .acknowledgementMode(AcknowledgementMode.ON_SUCCESS)
                .pollTimeout(Duration.ofSeconds(20)))
            .sqsAsyncClient(sqsAsyncClient)
            .build();
    }
}
