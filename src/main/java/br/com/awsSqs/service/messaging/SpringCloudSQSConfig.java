package br.com.awsSqs.service.messaging;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;

@Configuration
public class SpringCloudSQSConfig {
    @Value("${cloud.aws.sqs.region}")
    private String region;

    public AmazonSQSAsync amazonSQSAsync() {
        AwsCredentials awsCredentials = EnvironmentVariableCredentialsProvider.create().resolveCredentials();

        return AmazonSQSAsyncClientBuilder
                .standard()
                .withCredentials(
                        new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsCredentials.accessKeyId(),
                                awsCredentials.secretAccessKey())))
                .withRegion(Region.of(this.region).toString())
                .build();
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate() {
        return new
                QueueMessagingTemplate(this.amazonSQSAsync());
    }
}
