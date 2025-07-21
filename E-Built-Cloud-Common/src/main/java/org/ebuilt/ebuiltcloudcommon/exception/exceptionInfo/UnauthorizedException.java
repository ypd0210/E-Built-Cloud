package org.ebuilt.ebuiltcloudcommon.exception.exceptionInfo;

public class UnauthorizedException extends CommonException{

    public UnauthorizedException(String message) {
        super(message, 401);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause, 401);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause, 401);
    }
}
