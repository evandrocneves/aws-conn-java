// snippet-sourcedescription:[DetectSentiment demonstrates how to detect sentiments in the text.]
//snippet-keyword:[AWS SDK for Java v2]
// snippet-service:[Amazon Comprehend]
// snippet-keyword:[Code Sample]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[09/28/2021]
// snippet-sourceauthor:[scmacdon - AWS]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/
package br.com.awsSqs.service.nlp;

import br.com.awsSqs.domain.Sentiment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.comprehend.ComprehendClient;
import software.amazon.awssdk.services.comprehend.model.ComprehendException;
import software.amazon.awssdk.services.comprehend.model.DetectSentimentRequest;
import software.amazon.awssdk.services.comprehend.model.DetectSentimentResponse;

@Service
@Slf4j
public class NlpService {
    @Value("${cloud.aws.comprehend.language}")
    private String language;

    @Value("${cloud.aws.comprehend.region}")
    private String region;

    private static NlpService instance;

    public static NlpService getInstance() {
        return (instance == null ? new NlpService() : instance);
    }


    public Sentiment detectSentiment(String text) {
        Region region = Region.of(this.region);
        ComprehendClient comClient = getComprehendClient(region);
        Sentiment sentiment = null;

        try {
            DetectSentimentRequest detectSentimentRequest = DetectSentimentRequest.builder()
                    .text(text)
                    .languageCode(language)
                    .build();

            DetectSentimentResponse detectSentimentResult = comClient.detectSentiment(detectSentimentRequest);
            sentiment = new Sentiment(text, detectSentimentResult.sentimentScore().neutral(),
                    detectSentimentResult.sentimentScore().positive(),
                    detectSentimentResult.sentimentScore().negative());

        } catch (ComprehendException e) {
            log.error(e.awsErrorDetails().errorMessage());
            comClient.close();
            System.exit(1);
        } finally {
            comClient.close();
        }
        return sentiment;
    }

    public ComprehendClient getComprehendClient(Region region) {
        ComprehendClient comClient = ComprehendClient.builder()
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .region(region)
                .build();
        return comClient;
    }
}