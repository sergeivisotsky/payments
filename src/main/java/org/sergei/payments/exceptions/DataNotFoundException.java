package org.sergei.payments.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author Sergei Visotsky
 */
public class DataNotFoundException extends ServiceException {
    private static final long serialVersionUID = -8963595856565443443L;

    private final String errorCode;

    public DataNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public HttpStatus getHttpStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }
}
