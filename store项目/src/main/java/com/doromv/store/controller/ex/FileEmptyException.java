package com.doromv.store.controller.ex;

/**
 * @author Doromv
 * @create 2022-04-02-10:02
 */
public class FileEmptyException extends FileUploadException{
    public FileEmptyException() {
        super();
    }

    public FileEmptyException(String s) {
        super(s);
    }

    public FileEmptyException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public FileEmptyException(Throwable throwable) {
        super(throwable);
    }

    protected FileEmptyException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
