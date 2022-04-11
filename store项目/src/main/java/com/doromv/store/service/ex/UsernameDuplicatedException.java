package com.doromv.store.service.ex;

/**
 * @author Doromv
 * @create 2022-03-30-8:14
 */
public class UsernameDuplicatedException extends ServiceException{
    public UsernameDuplicatedException() {
        super();
    }

    public UsernameDuplicatedException(String s) {
        super(s);
    }

    public UsernameDuplicatedException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public UsernameDuplicatedException(Throwable throwable) {
        super(throwable);
    }

    protected UsernameDuplicatedException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
