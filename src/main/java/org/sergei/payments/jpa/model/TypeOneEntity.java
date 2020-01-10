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
public class TypeOneEntity extends TypeEntity {
    private static final long serialVersionUID = -1417746126366299776L;

    private String details;
}
