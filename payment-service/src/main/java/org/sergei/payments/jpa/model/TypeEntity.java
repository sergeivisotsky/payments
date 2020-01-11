package org.sergei.payments.jpa.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * @author Sergei Visotsky
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("SpellCheckingInspection")
@Entity
public class TypeEntity extends BaseEntity {
    private static final long serialVersionUID = 8746267163485821882L;

    private String typeNumber;

    @Column(insertable = false, updatable = false)
    private String dtype;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private CurrencyType currency;

    private String deptorIban;

    private String creditorIban;
}
