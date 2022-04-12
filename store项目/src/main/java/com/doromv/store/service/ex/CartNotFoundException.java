package com.doromv.store.service.ex;

/**
 * @author Doromv
 * @create 2022-04-12-10:43
 */
public class CartNotFoundException extends ServiceException {
    public CartNotFoundException() {
        super();
    }

    public CartNotFoundException(String s) {
        super(s);
    }

    public CartNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public CartNotFoundException(Throwable throwable) {
        super(throwable);
    }

    protected CartNotFoundException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
