package org.sergei.payments.rest.controller;

import java.math.BigDecimal;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.sergei.payments.rest.dto.PaymentRequestDTO;
import org.sergei.payments.rest.dto.PaymentResponseHolderDTO;
import org.sergei.payments.rest.dto.PaymentSummaryDTO;
import org.sergei.payments.rest.dto.ResponseDTO;
import org.sergei.payments.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@Api(tags = {"Payments operation"})
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{paymentNumber}")
    @ApiOperation(value = "Get specific payment by its number number")
    public ResponseEntity<ResponseDTO<PaymentSummaryDTO>> getPaymentByNumber(@PathVariable String paymentNumber) {
        return new ResponseEntity<>(paymentService.getPaymentByNumber(paymentNumber), HttpStatus.OK);
    }

    @GetMapping(params = {"amountFrom", "amountTo"})
    @ApiOperation(value = "Get IDs of all active payments as well as gives and ability to filter payment by amount")
    public ResponseEntity<ResponseDTO<PaymentResponseHolderDTO>> getIdsOfAllActiveAndFilterByAmount(@RequestParam("amountFrom") BigDecimal amountFrom,
                                                                                                    @RequestParam("amountTo") BigDecimal amountTo) {
        return new ResponseEntity<>(paymentService.getIdsOfAllActiveAndFilterByAmount(amountFrom, amountTo), HttpStatus.OK);
    }

    @PostMapping("/init")
    @ApiOperation(value = "Create payment for one of three payments TYPE1, TYPE2, TYPE3")
    public ResponseEntity<ResponseDTO<PaymentResponseHolderDTO>> initPayment(@RequestBody PaymentRequestDTO request) {
        return new ResponseEntity<>(paymentService.initPayment(request), HttpStatus.OK);
    }

    @PostMapping("/cancel")
    @ApiOperation(value = "Client should be able to cancel the payment. " +
            "It is possible to cancel payment only on the day of creation before 00:00. When cancel happens, " +
            "cancellation fee is being calculated and saved along the payment in database.")
    public ResponseEntity<ResponseDTO<PaymentSummaryDTO>> cancelPayment(@RequestParam("number") String paymentNumber) {
        return new ResponseEntity<>(paymentService.cancelPayment(paymentNumber), HttpStatus.OK);
    }
}
