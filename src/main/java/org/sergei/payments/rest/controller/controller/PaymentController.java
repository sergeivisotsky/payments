package org.sergei.payments.rest.controller.controller;

import java.math.BigDecimal;

import lombok.RequiredArgsConstructor;
import org.sergei.payments.rest.controller.dto.request.PaymentRequestDTO;
import org.sergei.payments.rest.controller.dto.response.PaymentResponseHolderDTO;
import org.sergei.payments.rest.controller.dto.response.ResponseDTO;
import org.sergei.payments.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sergei Visotsky
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping(params = {"amountFrom", "amountTo"})
    public ResponseEntity<ResponseDTO<PaymentResponseHolderDTO>> getIdsOfAllActiveAndFilterByAmount(@RequestParam("amountFrom") BigDecimal amountFrom,
                                                                                                    @RequestParam("amountTo") BigDecimal amountTo) {
        return new ResponseEntity<>(paymentService.getIdsOfAllActiveAndFilterByAmount(amountFrom, amountTo), HttpStatus.OK);
    }

    @PostMapping("/init")
    public ResponseEntity<ResponseDTO<PaymentResponseHolderDTO>> initPayment(@RequestBody PaymentRequestDTO request) {
        return new ResponseEntity<>(paymentService.initPayment(request), HttpStatus.OK);
    }
}
