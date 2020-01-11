package org.sergei.payments.rest;

import java.math.BigDecimal;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.sergei.payments.AbstractTest;
import org.sergei.payments.jpa.model.PaymentSummary;
import org.sergei.payments.jpa.repository.PaymentRepository;
import org.sergei.payments.rest.controller.PaymentController;
import org.sergei.payments.rest.dto.PaymentRequestDTO;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * Test for {@link PaymentController#getPaymentByNumber(String)}
     */
    @Test
    void getPaymentByNumberTest() throws JSONException {
        String initPaymentUrl = "http://localhost:" + port + "/payments/7ba7b804-6909-465a-9b2d-1b6fcb3e0680";
        ResponseEntity<String> response = restTemplate.exchange(initPaymentUrl, HttpMethod.GET, null, String.class);
        String actual = response.getBody();
        String expected = readFromJSONFile("json/payment_number_with_fee_response.json");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expected, actual, JSONCompareMode.STRICT);
    }

    /**
     * Test for {@link PaymentController#getIdsOfAllActiveAndFilterByAmount(BigDecimal, BigDecimal)}
     */
    @Test
    void getIdsOfAllActiveAndFilterByAmountTest() throws JSONException {
        String initPaymentUrl = "http://localhost:" + port + "/payments?amountFrom=100&amountTo=6000";
        ResponseEntity<String> response = restTemplate.exchange(initPaymentUrl, HttpMethod.GET, null, String.class);
        String actual = response.getBody();
        String expected = readFromJSONFile("json/payments_numbers_filter_response.json");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expected, actual, JSONCompareMode.STRICT);
    }

    /**
     * Test for {@link PaymentController#initPayment(PaymentRequestDTO)}
     */
    @Test
    void initPaymentTest() throws JSONException {
        String initPaymentUrl = "http://localhost:" + port + "/payments/init";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(readFromJSONFile("json/payment_request.json"), headers);
        ResponseEntity<String> response = restTemplate.exchange(initPaymentUrl, HttpMethod.POST, request, String.class);
        String actual = response.getBody();
        String expected = readFromJSONFile("json/success_response.json");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expected, actual, JSONCompareMode.STRICT);
    }

    /**
     * Test for {@link PaymentController#cancelPayment(String)}
     */
    @Disabled
    @Test
    void cancelPaymentTest() throws JsonProcessingException {
        // Create payment
        String initPaymentUrl = "http://localhost:" + port + "/payments/init";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(readFromJSONFile("json/payment_request.json"), headers);
        ResponseEntity<String> response = restTemplate.exchange(initPaymentUrl, HttpMethod.POST, request, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response.getBody());

        // Perform payment cancellation
        String cancelPaymentUrl = "http://localhost:" + port + "/payments/cancel";
        restTemplate.exchange(cancelPaymentUrl, HttpMethod.POST, null, String.class);
        Optional<PaymentSummary> paymentSummary = paymentRepository.findPaymentByNumber(node.get("paymentNumber").asText());
        assertEquals(BigDecimal.ZERO, paymentSummary.get().getCancellationFee());
    }
}
