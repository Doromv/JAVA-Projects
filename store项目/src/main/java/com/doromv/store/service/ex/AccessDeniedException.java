package com.doromv.store.service.ex;

/**
 * @author Doromv
 * @create 2022-04-05-11:18
 */
public class AccessDeniedException extends ServiceException{
    public AccessDeniedException() {
        super();
    }

    public AccessDeniedException(String s) {
        super(s);
    }

    public AccessDeniedException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public AccessDeniedException(Throwable throwable) {
        super(throwable);
    }

    protected AccessDeniedException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
