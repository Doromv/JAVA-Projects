package com.doromv.store.service.ex;

/**
 * @author Doromv
 * @create 2022-03-30-15:50
 */
public class PasswordNotMatchExpection extends ServiceException{
    public PasswordNotMatchExpection() {
        super();
    }

    public PasswordNotMatchExpection(String s) {
        super(s);
    }

    public PasswordNotMatchExpection(String s, Throwable throwable) {
        super(s, throwable);
    }

    public PasswordNotMatchExpection(Throwable throwable) {
        super(throwable);
    }

    protected PasswordNotMatchExpection(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
