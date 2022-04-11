package com.doromv.store.service.ex;

/**
 * @author Doromv
 * @create 2022-03-30-15:51
 */
public class UsernameNotFoundException extends ServiceException{
    public UsernameNotFoundException() {
        super();
    }

    public UsernameNotFoundException(String s) {
        super(s);
    }

    public UsernameNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public UsernameNotFoundException(Throwable throwable) {
        super(throwable);
    }

    protected UsernameNotFoundException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
