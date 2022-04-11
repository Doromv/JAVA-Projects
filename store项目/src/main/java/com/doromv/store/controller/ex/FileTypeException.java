package com.doromv.store.controller.ex;

/**
 * @author Doromv
 * @create 2022-04-02-10:05
 */
public class FileTypeException extends FileUploadException{
    public FileTypeException() {
        super();
    }

    public FileTypeException(String s) {
        super(s);
    }

    public FileTypeException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public FileTypeException(Throwable throwable) {
        super(throwable);
    }

    protected FileTypeException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
