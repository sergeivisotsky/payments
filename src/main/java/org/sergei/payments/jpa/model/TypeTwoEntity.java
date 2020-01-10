package org.sergei.payments.jpa.model;

import javax.persistence.Entity;

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
public class TypeTwoEntity extends TypeEntity {
    private static final long serialVersionUID = 6679708597916556530L;

    private String details;
}
