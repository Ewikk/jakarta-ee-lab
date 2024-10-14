package com.pg.jakartaeelab.controller.servlet.exception;


/**
 * Exception indicates that requested resource was not found and 404 status code should be returned.
 */
public class NotFoundException extends HttpRequestException {

    /**
     * HTTP not found response code.
     */
    private static final int RESPONSE_CODE = 404;

    public NotFoundException() {
        super(RESPONSE_CODE);
    }

    /**
     * @param message the detail message
     */
    public NotFoundException(String message) {
        super(message, RESPONSE_CODE);
    }

    /**
     * @param message the detail message
     * @param cause   the cause
     */
    public NotFoundException(String message, Throwable cause) {
        super(message, cause, RESPONSE_CODE);
    }

    /**
     * @param cause the cause
     */
    public NotFoundException(Throwable cause) {
        super(cause, RESPONSE_CODE);
    }

    /**
     * @param message            the detail message
     * @param cause              the cause
     * @param enableSuppression  whether suppression is enabled or disabled
     * @param writableStackTrace whether the stack trace should be writable
     */
    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace, RESPONSE_CODE);
    }

}
