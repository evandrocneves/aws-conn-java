package br.com.awsSqs.service.messaging;


import br.com.awsSqs.domain.Sentiment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/produce")
public class SqsController {

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.end-point.uri}")
    private String endPoint;

    @PostMapping("/sentiment")
    public String sendMessage(@RequestBody Sentiment text) {
        String textToAnalyze = text.getText();
        try {
            String messageId = UUID.randomUUID().toString();
            log.info("Message sent: " + messageId);

            queueMessagingTemplate.send(endPoint,
                    MessageBuilder.withPayload(textToAnalyze)
                            .setHeader("message-group-id", messageId)
                            .setHeader("message-deduplication-id", messageId)
                            .build());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return textToAnalyze;
    }
}
