/*
 * Copyright © 2018-2020 Sergei Visotsky as an original author.
 * No portion of this work may be copied, distributed, modified, or incorporated
 * into any other media without Sergei Visotsky’s prior written consent.
 */

package org.sergei.payments.rest.controller.dto.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Sergei Visotsky
 */
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseErrorDTO implements Serializable {

    private static final long serialVersionUID = 7356941682270970714L;

    private String errorCode;
    private String errorDescription;
    private String errorType;
}
