package org.sergei.payments.rest.controller;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.sergei.payments.AbstractTest;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Sergei Visotsky
 */
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentControllerTest extends AbstractTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void initPaymentTest() throws JSONException {
        String initPaymentUrl = "http://localhost:" + port + "/payments/init";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(readFromJSONFile("json/payment_request.json"), headers);
        ResponseEntity<String> response = restTemplate.exchange(initPaymentUrl, HttpMethod.POST, request, String.class);
        String actual = response.getBody();
        String expected = readFromJSONFile("json/success_response.json");
        JSONAssert.assertEquals(expected, actual, JSONCompareMode.STRICT);
    }
}
