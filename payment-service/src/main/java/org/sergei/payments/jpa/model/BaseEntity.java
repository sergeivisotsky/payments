package org.sergei.payments.jpa.model;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Sergei Visotsky
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 7029248787143137693L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "system_seq")
    @SequenceGenerator(name = "system_seq",
            sequenceName = "system_seq", allocationSize = 1000)
    private Long id;
}
