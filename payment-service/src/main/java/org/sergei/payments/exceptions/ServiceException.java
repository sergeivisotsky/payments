package org.sergei.payments.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author Sergei Visotsky
 */
public abstract class ServiceException extends ServiceRuntimeException {
    private static final long serialVersionUID = -8330306067783475020L;

    public abstract HttpStatus getHttpStatusCode();

    public abstract String getErrorCode();

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Object... args) {
        super(message, args);
    }

    public ServiceException(String message, Throwable cause, Object... args) {
        super(message, cause, args);
    }
}
