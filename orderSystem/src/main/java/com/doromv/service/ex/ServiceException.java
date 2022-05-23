package com.doromv.service.ex;

/**
 * @author Doromv
 * @create 2022-03-30-8:10
 */
public class ServiceException extends RuntimeException{
    public ServiceException() {
        super();
    }

    public ServiceException(String s) {
        super(s);
    }

}
