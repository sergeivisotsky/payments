package org.sergei.payments.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ListenerApplication {

    private static final Logger log = LoggerFactory.getLogger(ListenerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ListenerApplication.class, args);
    }

    @GetMapping("/notify/{paymentNumber}")
    public ResponseEntity<String> notifyAboutPayment(@PathVariable String paymentNumber) {
        log.info("Payment with number: {} received", paymentNumber);
        return new ResponseEntity<>(paymentNumber, HttpStatus.OK);
    }
}
