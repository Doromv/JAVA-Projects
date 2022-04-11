package com.doromv.store.service.ex;

/**
 * @author Doromv
 * @create 2022-04-05-11:17
 */
public class AddressNotFoundException extends ServiceException{
    public AddressNotFoundException() {
        super();
    }

    public AddressNotFoundException(String s) {
        super(s);
    }

    public AddressNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public AddressNotFoundException(Throwable throwable) {
        super(throwable);
    }

    protected AddressNotFoundException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
