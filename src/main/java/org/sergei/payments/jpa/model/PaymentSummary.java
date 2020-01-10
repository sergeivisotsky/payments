package org.sergei.payments.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Sergei Visotsky
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PaymentSummary extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 4809061982983832193L;

    private String paymentNumber;
    private BigDecimal totalAmount;
    private String firstName;
    private String lastName;
    private String cardNumber;

    @Setter
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private LocalDateTime creationDate;

    @Setter
    private BigDecimal cancellationFee;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH})
    @JoinColumn(name = "type_id",
            referencedColumnName = "id")
    private TypeEntity type;
}
