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
package elections.service.comprehend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import elections.domain.Sentiment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.comprehend.ComprehendClient;
import software.amazon.awssdk.services.comprehend.model.ComprehendException;
import software.amazon.awssdk.services.comprehend.model.DetectSentimentRequest;
import software.amazon.awssdk.services.comprehend.model.DetectSentimentResponse;

@Service
@Slf4j
public class DetectSentimentService {

    public static void main(String[] args) {

//        String text = "Amazon.com, Inc. is located in Seattle, WA and was founded July 5th, 1994 by Jeff Bezos, allowing customers to buy everything from books to blenders. Seattle is north of Portland and south of Vancouver, BC. Other notable Seattle - based companies are Starbucks and Boeing.";
        String text = "A menina é bem feia";

        System.out.println("Calling DetectSentiment");
        //detectSentiments(text);

    }

    public String detectSentiment(String text) {
        Region region = Region.US_EAST_1;

        ComprehendClient comClient = ComprehendClient.builder()
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .region(region)
                .build();

        String json = "";
        try {
            DetectSentimentRequest detectSentimentRequest = DetectSentimentRequest.builder()
                    .text(text)
                    .languageCode("pt")
                    .build();

            DetectSentimentResponse detectSentimentResult = comClient.detectSentiment(detectSentimentRequest);
            Sentiment sentiment = new Sentiment(detectSentimentResult.sentimentScore().neutral(),
                    detectSentimentResult.sentimentScore().positive(),
                    detectSentimentResult.sentimentScore().negative());

            ObjectMapper mapper = new ObjectMapper();

            try {
                json = mapper.writeValueAsString(sentiment);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        } catch (ComprehendException e) {
            log.error(e.awsErrorDetails().errorMessage());
            comClient.close();
            System.exit(1);
        }
        return json;
    }
}