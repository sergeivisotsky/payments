package org.sergei.payments.exceptions;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.sergei.payments.rest.controller.dto.response.ResponseErrorDTO;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Sergei Visotsky
 */
@Slf4j
@Component
public class UnhandledExceptionProcessor extends BasicErrorController {

    public UnhandledExceptionProcessor(ErrorAttributes errorAttributes) {
        super(errorAttributes, new ErrorProperties());
    }

    @GetMapping(produces = "application/json")
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        ResponseErrorDTO errorUnit = ResponseErrorDTO.builder()
                .errorCode("GLOBAL_001")
                .errorType("ERROR")
                .errorDescription("Error on the microservice side")
                .build();
        Map<String, Object> error = new HashMap<>();
        error.put("error", ImmutableList.of(errorUnit));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
