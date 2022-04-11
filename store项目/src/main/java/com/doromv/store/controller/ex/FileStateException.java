package com.doromv.store.controller.ex;

/**
 * @author Doromv
 * @create 2022-04-02-10:03
 */
public class FileStateException extends FileUploadException{
    public FileStateException() {
        super();
    }

    public FileStateException(String s) {
        super(s);
    }

    public FileStateException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public FileStateException(Throwable throwable) {
        super(throwable);
    }

    protected FileStateException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
