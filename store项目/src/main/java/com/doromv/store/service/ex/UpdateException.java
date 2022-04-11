package com.doromv.store.service.ex;

/**
 * @author Doromv
 * @create 2022-03-31-16:54
 */
public class UpdateException extends ServiceException {
    public UpdateException() {
        super();
    }

    public UpdateException(String s) {
        super(s);
    }

    public UpdateException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public UpdateException(Throwable throwable) {
        super(throwable);
    }

    protected UpdateException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
