package com.doromv.store.controller.ex;

/**
 * @author Doromv
 * @create 2022-04-02-10:03
 */
public class FileSizeException extends FileUploadException{
    public FileSizeException() {
        super();
    }

    public FileSizeException(String s) {
        super(s);
    }

    public FileSizeException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public FileSizeException(Throwable throwable) {
        super(throwable);
    }

    protected FileSizeException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
