package com.doromv.store.controller.ex;

/**
 * @author Doromv
 * @create 2022-04-02-10:04
 */
public class FileUploadIOException extends FileUploadException{
    public FileUploadIOException() {
        super();
    }

    public FileUploadIOException(String s) {
        super(s);
    }

    public FileUploadIOException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public FileUploadIOException(Throwable throwable) {
        super(throwable);
    }

    protected FileUploadIOException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
