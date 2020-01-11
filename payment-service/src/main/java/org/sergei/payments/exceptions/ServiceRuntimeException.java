/*
 * Copyright © 2018-2020 Sergei Visotsky as an original author.
 * No portion of this work may be copied, distributed, modified, or incorporated
 * into any other media without Sergei Visotsky’s prior written consent.
 */

package org.sergei.payments.exceptions;

/**
 * @author Sergei Visotsky
 */
public class ServiceRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -1306800962027270533L;

    public ServiceRuntimeException(String message) {
        super(message);
    }

    public ServiceRuntimeException(String message, Object... args) {
        super(String.format(message.replace("{}", "%s"), args), null);
    }

    public ServiceRuntimeException(String message, Throwable cause, Object... args) {
        super(String.format(message.replace("{}", "%s"), args), cause);
    }

    public ServiceRuntimeException(Throwable cause) {
        super(cause);
    }
}
