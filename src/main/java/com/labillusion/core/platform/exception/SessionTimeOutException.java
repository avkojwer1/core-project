package com.labillusion.core.platform.exception;

/**
 * Created by greg.chen on 14-10-21.
 */
public class SessionTimeOutException extends RuntimeException {
    public SessionTimeOutException(String message) {
        super(message);
    }

    public SessionTimeOutException(String message, Throwable cause) {
        super(message, cause);
    }
}
