package org.sergei.payments.rest.dto;

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
