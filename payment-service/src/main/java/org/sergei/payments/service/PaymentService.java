package org.sergei.payments.service;

import java.math.BigDecimal;

import org.sergei.payments.rest.dto.PaymentRequestDTO;
import org.sergei.payments.rest.dto.PaymentResponseHolderDTO;
import org.sergei.payments.rest.dto.PaymentSummaryDTO;
import org.sergei.payments.rest.dto.ResponseDTO;

/**
 * @author Sergei Visotsky
 */
public interface PaymentService {

    /**
     * Query all payments that aren't canceled as well as filter them by amount.
     *
     * @param amountFrom amount to filter from
     * @param amountTo   amount to filter to
     * @return Payment summary of numbers (IDs)
     */
    ResponseDTO<PaymentResponseHolderDTO> getIdsOfAllActiveAndFilterByAmount(BigDecimal amountFrom, BigDecimal amountTo);

    /**
     * Query specific payment by number (ID)
     *
     * @param paymentNumber payment number (ID)
     * @return payment number (ID) and cancellation fee response DTO
     */
    ResponseDTO<PaymentSummaryDTO> getPaymentByNumber(String paymentNumber);

    /**
     * Create payment for one of three types
     *
     * @param request payment request collected from UI
     * @return response
     */
    ResponseDTO<PaymentResponseHolderDTO> initPayment(PaymentRequestDTO request);

    /**
     * Cancel payment by number (ID)
     *
     * @param paymentNumber payment number e.g. ID
     * @return cancelled payment number (ID) and cancellation fee holder
     */
    ResponseDTO<PaymentSummaryDTO> cancelPayment(String paymentNumber);
}
