/*
 * Copyright © 2018-2020 Sergei Visotsky as an original author.
 * No portion of this work may be copied, distributed, modified, or incorporated
 * into any other media without Sergei Visotsky’s prior written consent.
 */

package org.sergei.payments.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Sergei Visotsky
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "error_messages")
public class ResponseMessage extends BaseEntity {

    private static final long serialVersionUID = 3347750147941014197L;

    @Column(name = "code")
    private String code;

    @Column(name = "message")
    private String message;

}
