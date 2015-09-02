package com.cn.encoder.exceptions;

@SuppressWarnings("serial")
public class PathCheckFailureException extends Exception {

    public PathCheckFailureException() {
    }

    public PathCheckFailureException(String arg0) {
        super(arg0);
    }

    public PathCheckFailureException(Throwable arg0) {
        super(arg0);
    }

    public PathCheckFailureException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public PathCheckFailureException(String arg0, Throwable arg1, boolean arg2,
                                     boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}
