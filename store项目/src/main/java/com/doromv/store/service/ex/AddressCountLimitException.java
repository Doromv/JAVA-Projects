package com.doromv.store.service.ex;

/**
 * @author Doromv
 * @create 2022-04-04-9:03
 */
public class AddressCountLimitException extends ServiceException{
    public AddressCountLimitException() {
        super();
    }

    public AddressCountLimitException(String s) {
        super(s);
    }

    public AddressCountLimitException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public AddressCountLimitException(Throwable throwable) {
        super(throwable);
    }

    protected AddressCountLimitException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
