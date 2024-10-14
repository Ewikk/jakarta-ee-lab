package com.pg.jakartaeelab.controller.servlet.exception;


/**
 * Exception indicates that client made bad request and 400 status code should be returned.
 */
public class BadRequestException extends HttpRequestException {

    /**
     * HTTP bad request response code.
     */
    private static final int RESPONSE_CODE = 400;


    public BadRequestException() {
        super(RESPONSE_CODE);
    }

    /**
     * @param message the detail message
     */
    public BadRequestException(String message) {
        super(message, RESPONSE_CODE);
    }

    /**
     * @param message the detail message
     * @param cause   the cause
     */
    public BadRequestException(String message, Throwable cause) {
        super(message, cause, RESPONSE_CODE);
    }

    /**
     * @param cause the cause
     */
    public BadRequestException(Throwable cause) {
        super(cause, RESPONSE_CODE);
    }

    /**
     * @param message            the detail message
     * @param cause              the cause
     * @param enableSuppression  whether suppression is enabled or disabled
     * @param writableStackTrace whether the stack trace should be writable
     */
    public BadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace, RESPONSE_CODE);
    }

}
