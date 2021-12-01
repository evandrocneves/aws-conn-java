package br.com.awsSqs.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = {"br.com.awsSqs*"},
        exclude = {org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration.class,
                org.springframework.cloud.aws.autoconfigure.context.ContextCredentialsAutoConfiguration.class,
                org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration.class,
                org.springframework.cloud.aws.autoconfigure.context.ContextResourceLoaderAutoConfiguration.class,
                org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration.class,
                org.springframework.cloud.aws.autoconfigure.mail.MailSenderAutoConfiguration.class,
                org.springframework.cloud.aws.autoconfigure.cache.ElastiCacheAutoConfiguration.class,
                org.springframework.cloud.aws.autoconfigure.messaging.MessagingAutoConfiguration.class,
                org.springframework.cloud.aws.autoconfigure.jdbc.AmazonRdsDatabaseAutoConfiguration.class,
                org.springframework.cloud.aws.autoconfigure.metrics.CloudWatchExportAutoConfiguration.class
        })
public class SpringBootSqsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSqsApplication.class, args);
    }
}
