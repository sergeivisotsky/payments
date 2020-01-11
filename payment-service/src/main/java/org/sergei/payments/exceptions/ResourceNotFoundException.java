package org.sergei.payments.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author Sergei Visotsky
 */
public class ResourceNotFoundException extends ServiceException {
    private static final long serialVersionUID = 7798710995096075398L;

    private final String errorCode;

    @Override
    public HttpStatus getHttpStatusCode() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    public ResourceNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
