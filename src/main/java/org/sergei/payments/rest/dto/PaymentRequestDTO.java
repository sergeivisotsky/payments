package org.sergei.payments.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Sergei Visotsky
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {
    private String firstName;
    private String lastName;
    private String cardNumber;
    private String typeNumber;
}
