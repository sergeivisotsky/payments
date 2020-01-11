package org.sergei.payments.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author Sergei Visotsky
 */
public class DataIntegrityException extends ServiceException {
    private static final long serialVersionUID = 1931780157488375236L;

    private final String errorCode;

    @Override
    public HttpStatus getHttpStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    public DataIntegrityException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public DataIntegrityException(String message, String errorCode, Object... args) {
        super(String.format(message.replace("{}", "%s"), args));
        this.errorCode = errorCode;
    }
}
