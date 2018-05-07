package com.hania;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port=0"})
public class ShoppingApplicationTests {

    private static final String HOST = "http://localhost:";

    @LocalServerPort
    private int port;

    @Value("${local.management.port}")
    private int mgt;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturn200WhenSendingRequestToController() {
        // when
        ResponseEntity<Map> entity = testRestTemplate.getForEntity(
                HOST + this.port + "/shopping/order/0", Map.class);

        // then
        String message = String.format("Order with id %d not found", 0);
        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(entity.getBody()).containsValue(message);
    }

    @Test
    public void shouldReturn200WhenSendingRequestToManagementEndpoint() {
        // when
        ResponseEntity<Map> entity = testRestTemplate.getForEntity(
                HOST + this.mgt + "/actuator/info", Map.class);

        // then
        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
