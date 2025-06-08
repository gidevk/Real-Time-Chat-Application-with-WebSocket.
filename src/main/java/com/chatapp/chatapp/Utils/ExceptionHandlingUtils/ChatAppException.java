package com.chatapp.chatapp.Utils.ExceptionHandlingUtils;

public class ChatAppException extends RuntimeException {

    public ChatAppException() {
        super();
    }

    public ChatAppException(String message) {
        super(message);
    }

    public ChatAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChatAppException(Throwable cause) {
        super(cause);
    }

    protected ChatAppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
