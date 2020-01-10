package org.sergei.payments.exceptions;

import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.sergei.payments.rest.controller.dto.response.ResponseDTO;
import org.sergei.payments.service.ResponseMessageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Sergei Visotsky
 */
@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    private final ResponseMessageService messageService;

    @ExceptionHandler(ServiceException.class)
    public final ResponseEntity<ResponseDTO> serviceException(ServiceException e) {
        log.info("{} triggered", this.getClass().getName());
        log.error(ExceptionUtils.getStackTrace(e));
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .errorList(messageService.responseErrorListByCode(e.getErrorCode()))
                        .response(ImmutableList.of())
                        .build(),
                new HttpHeaders(), e.getHttpStatusCode());
    }
}
