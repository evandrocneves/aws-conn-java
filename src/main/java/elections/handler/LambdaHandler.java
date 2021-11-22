package elections.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import elections.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

import java.util.List;
import java.util.Map;

@Slf4j
public class LambdaHandler extends SpringBootRequestHandler<APIGatewayProxyRequestEvent, List<Order>> {
//    @Override
//    public Object handleRequest(APIGatewayProxyRequestEvent event, Context context) {
//        if (event != null) {
//            event.getQueryStringParameters().forEach((key, value) ->
//                    log.info("ECN Handler: " + value.toString()));
//        }
//
//        return super.handleRequest(event, context);
//    }

}