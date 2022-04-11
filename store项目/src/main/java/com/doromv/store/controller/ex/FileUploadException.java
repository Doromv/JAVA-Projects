package com.doromv.store.controller.ex;

/**
 * @author Doromv
 * @create 2022-04-02-9:59
 */
public class FileUploadException extends RuntimeException{
    public FileUploadException() {
        super();
    }

    public FileUploadException(String s) {
        super(s);
    }

    public FileUploadException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public FileUploadException(Throwable throwable) {
        super(throwable);
    }

    protected FileUploadException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
