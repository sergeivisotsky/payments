package org.sergei.payments.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Sergei Visotsky
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cancellation_coefficients")
public class CancellationCoefficient extends BaseEntity {

    private static final long serialVersionUID = -4312153552900378777L;

    @Column(name = "type_d_type")
    private String typeDType;
    private Double coefficient;

}
