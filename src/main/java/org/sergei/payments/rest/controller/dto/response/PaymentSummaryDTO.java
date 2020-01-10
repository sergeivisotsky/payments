package org.sergei.payments.rest.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Sergei Visotsky
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentSummaryDTO {
    private String paymentNumber;
    private String cancellationFee;
}
