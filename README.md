# Exploring AWS Services with Java in depth

This project has been created in order to use AWS services using Spring Boot.
Onto this project, you can find some examples regarding these services:
## 1) AWS Simple Queue Service (SQS)
By using this message queue processing service,
On the other hand, in case of need a pub-sub service, it's highly recommended to use another AWS Service called SNS (Simple Notification Service).
You can refer this recommendation into this link: xxxxxx

## 2) AWS Comprehend
This service is intended for natural-language processing. It processes text which collects entities, key phrases as well as sentiments.
Specifically for sentiment analysis, it's possible to analyze any expression neutral, positive or negative.


## PreReqs
1) Have an AWS account. Right after that, provide your credentials into your OS environment variables.
2) Create an SQS queue and fill out cloud.aws.end-point.uri property

## How it works
After initializing Spring Boot App, use an API testing solution of your preference.  
In my case, I'd rather use Postman. 

This URL http://localhost:9091/produce/sentiment must be submitted as below:
![img.png](postman.png)

![img.png](log.png)

## To be continued
Please, contribute to this project by sharing ideas... My purpose is to help people to emerge to this technology    
