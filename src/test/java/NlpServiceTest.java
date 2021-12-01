import br.com.awsSqs.domain.Sentiment;
import br.com.awsSqs.service.nlp.NlpService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.comprehend.ComprehendClient;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {NlpService.class})
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NlpServiceTest {
    @Autowired
    NlpService nlpService;

    private static Region region;
    private static String neutralExpression;
    private static String positiveExpression;
    private static String negativeExpression;
    private static String language;

    @BeforeAll
    public static void setUp() throws URISyntaxException {
        region = Region.US_EAST_1;
        language = "en";
        neutralExpression = "That guy is wearing a mask!";
        positiveExpression = "Brazil's health system is very useful!";
        negativeExpression = "COVID-19 is a very terrifying disease";
    }

    @Test
    @Order(1)
    public void whenInitializingAWSService_thenNotNull() {
        ComprehendClient conn = nlpService.getComprehendClient(region);
        assertNotNull(conn);
        System.out.println("AWS Comprehend connection passed");
        conn.close();
    }

    @Test
    @Order(2)
    public void neutralExpression() {
        Sentiment result = nlpService.detectSentiment(neutralExpression);
        assertTrue(result.getNeutral() > result.getPositive() &&
                result.getNeutral() > result.getNegative());
        System.out.println("Neutral expression test passed");
    }

    @Test
    @Order(3)
    public void negativeExpression() {
        Sentiment result = nlpService.detectSentiment(negativeExpression);
        assertTrue(result.getNegative() > result.getPositive() &&
                result.getNeutral() < result.getNegative());
        System.out.println("Negative expression test passed");
    }

    @Test
    @Order(4)
    public void positiveExpression() {
        Sentiment result = nlpService.detectSentiment(positiveExpression);
        assertTrue(result.getNegative() < result.getPositive() &&
                result.getNeutral() < result.getPositive());
        System.out.println("Negative expression test passed");
    }
}
