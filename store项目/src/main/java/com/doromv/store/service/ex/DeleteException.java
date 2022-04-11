package com.doromv.store.service.ex;

/**
 * @author Doromv
 * @create 2022-04-07-8:30
 */
public class DeleteException extends ServiceException{
    public DeleteException() {
        super();
    }

    public DeleteException(String s) {
        super(s);
    }

    public DeleteException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DeleteException(Throwable throwable) {
        super(throwable);
    }

    protected DeleteException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
