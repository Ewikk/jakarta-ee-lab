package com.pg.jakartaeelab.controller.servlet.exception;

/**
 * Exception indicates that the requested resource already exists and a 409 status code should be returned.
 */
public class AlreadyExistsException extends HttpRequestException {

    /**
     * HTTP conflict response code.
     */
    private static final int RESPONSE_CODE = 409;

    public AlreadyExistsException() {
        super(RESPONSE_CODE);
    }

    /**
     * @param message the detail message
     */
    public AlreadyExistsException(String message) {
        super(message, RESPONSE_CODE);
    }

    /**
     * @param message the detail message
     * @param cause   the cause
     */
    public AlreadyExistsException(String message, Throwable cause) {
        super(message, cause, RESPONSE_CODE);
    }

    /**
     * @param cause the cause
     */
    public AlreadyExistsException(Throwable cause) {
        super(cause, RESPONSE_CODE);
    }

    /**
     * @param message            the detail message
     * @param cause              the cause
     * @param enableSuppression  whether suppression is enabled or disabled
     * @param writableStackTrace whether the stack trace should be writable
     */
    public AlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace, RESPONSE_CODE);
    }

}
