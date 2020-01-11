package org.sergei.payments.jpa.model;

import javax.persistence.Entity;
import javax.persistence.Table;

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
@Entity
public class PaymentTransferLog extends BaseEntity {

    private static final long serialVersionUID = -4650810737344044828L;

    private String paymentNumber;
    private Integer httpStatusCode;
    private String httpComment;
}
