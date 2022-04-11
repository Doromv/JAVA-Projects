package com.doromv.store.service.ex;

/**
 * @author Doromv
 * @create 2022-03-30-8:18
 */
public class InsertException extends ServiceException{
    public InsertException() {
        super();
    }

    public InsertException(String s) {
        super(s);
    }

    public InsertException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InsertException(Throwable throwable) {
        super(throwable);
    }

    protected InsertException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
