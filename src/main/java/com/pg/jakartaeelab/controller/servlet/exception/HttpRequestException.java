package com.pg.jakartaeelab.controller.servlet.exception;


import lombok.Getter;

/**
 * Exception thrown when there is error with HTTP request.
 */
public class HttpRequestException extends RuntimeException {

    /**
     * HTTP response code.
     */
    @Getter
    private final int responseCode;

    /**
     * @param responseCode HTTP response code
     */
    public HttpRequestException(int responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * @param message      the detail message
     * @param responseCode HTTP response code
     */
    public HttpRequestException(String message, int responseCode) {
        super(message);
        this.responseCode = responseCode;
    }

    /**
     * @param message      the detail message
     * @param cause        the cause
     * @param responseCode HTTP response code
     */
    public HttpRequestException(String message, Throwable cause, int responseCode) {
        super(message, cause);
        this.responseCode = responseCode;
    }

    /**
     * @param cause        the cause
     * @param responseCode HTTP response code
     */
    public HttpRequestException(Throwable cause, int responseCode) {
        super(cause);
        this.responseCode = responseCode;
    }

    /**
     * @param message            the detail message
     * @param cause              the cause
     * @param enableSuppression  whether suppression is enabled or disabled
     * @param writableStackTrace whether the stack trace should be writable
     * @param responseCode       HTTP response code
     */
    public HttpRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int responseCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.responseCode = responseCode;
    }

}
