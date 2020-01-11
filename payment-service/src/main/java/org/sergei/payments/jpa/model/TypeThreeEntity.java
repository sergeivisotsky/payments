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
public class TypeThreeEntity extends TypeEntity {
    private static final long serialVersionUID = -9119532400077796488L;

    private String bicCode;
}
