package org.sergei.payments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.sergei.payments.exceptions.ServiceRuntimeException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootApplication
public class PaymentsApplication {

    /**
     * Resolve clients country (use any external web service to resolve it by user IP)
     * and write it to the log (that’s OK if it will fail sometimes).
     * Information about clients country won't be required anywhere in business logic.
     *
     * @return {@link CommandLineRunner#run(String...)}
     */
    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            try {
                URL url = new URL("http://bot.whatismyipaddress.com");
                BufferedReader sc = new BufferedReader(new InputStreamReader(url.openStream()));
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> response = restTemplate.getForEntity("http://ip-api.com/json/" + sc.readLine().trim(), String.class);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(response.getBody());
                log.info("Country of your IP: {}", node.get("country").asText());
            } catch (UnknownHostException e) {
                throw new ServiceRuntimeException("Unable to resolve host");
            } catch (IOException e) {
                throw new ServiceRuntimeException("Unable to process JSON");
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(PaymentsApplication.class, args);
    }
}
