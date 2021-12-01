package br.com.awsSqs.service.messaging;

import br.com.awsSqs.domain.Sentiment;
import br.com.awsSqs.service.nlp.NlpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableSqs
public class SqsMessageReceiver {
    @Autowired
    NlpService nlpService;

    @SqsListener("${cloud.aws.end-point.uri}")
    public void receiveMessage(String text) {
        log.info("Message received SQS Listener - " + text);
        Sentiment result = nlpService.detectSentiment(text);
        log.info("Result Comprehend - " + result);
        //TODO Record to DynamoDB for analytics purposes
    }
}
