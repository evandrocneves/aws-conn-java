package elections.service;

import elections.service.comprehend.DetectSentimentService;
import elections.domain.Order;
import elections.repository.OrderDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication(scanBasePackages = {"elections.*"})
public class SpringbootAwsLambdaApplication {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private DetectSentimentService detectSentimentService;

    @Bean
    public Supplier<List<Order>> orders() {
        return () -> orderDao.buildOrders();
    }

//    @Bean
//    public Function<APIGatewayProxyRequestEvent, List<Order>> findOrderByName() {
//        return (requestEvent -> this.orders().get()
//                .stream()
//                .filter(order -> order.getName().equals(requestEvent.getQueryStringParameters().get("orderName")))
//                .collect(Collectors.toList()));
//    }
//

    @Bean
    public Function<Map<String, String>, List<Order>> findOrderByName() {
        return (input -> this.orders().get()
                .stream()
                .filter(order -> order.getName().equals(input.get("orderName")))
                .collect(Collectors.toList()));
    }

    @Bean
    public Function<Map<String, String>, String> detectSentiment() {
        return (input -> detectSentimentService.detectSentiment(input.get("text")));
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAwsLambdaApplication.class, args);
    }

}