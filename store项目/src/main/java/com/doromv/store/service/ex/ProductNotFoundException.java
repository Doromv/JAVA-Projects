package com.doromv.store.service.ex;

/**
 * @author Doromv
 * @create 2022-04-11-9:00
 */
public class ProductNotFoundException extends ServiceException{
    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String s) {
        super(s);
    }

    public ProductNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ProductNotFoundException(Throwable throwable) {
        super(throwable);
    }

    protected ProductNotFoundException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
